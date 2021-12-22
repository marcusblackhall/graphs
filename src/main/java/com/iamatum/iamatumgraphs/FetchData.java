package com.iamatum.iamatumgraphs;

import com.iamatum.iamatumgraphs.converters.ConvertCsvToList;
import com.iamatum.iamatumgraphs.domain.Lcps;
import com.iamatum.iamatumgraphs.mappers.LcpsMapper;
import com.iamatum.iamatumgraphs.model.LcpsData;
import com.iamatum.iamatumgraphs.repositories.LcpsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class FetchData {

    @Value("${lcps.url}")
    private String lcpsUrl;

    private final ConvertCsvToList<LcpsData> convertCsvToList;
    private final LcpsRepository lcpsRepository;
    private final LcpsMapper lcpsMapper;
    private final WebClient webClient;

    public void retrieveLcpsFigures() throws IOException {

        Flux<DataBuffer> dataBufferFlux = webClient.get()
                .uri(lcpsUrl)
                .retrieve()
                .bodyToFlux(DataBuffer.class);

        Path tempFile = Files.createTempFile("lcps", "csv");

        DataBufferUtils.write(dataBufferFlux, tempFile)
                .subscribe(unused -> {
                        },
                        throwable -> log.info("thrown error {}", throwable.getMessage()),
                        () -> {
                            log.info("Retrieved all data from lcps");
                            try (InputStream inputStream = Files.newInputStream(tempFile, StandardOpenOption.CREATE)) {
                                List<LcpsData> lcpsData = convertCsvDataToLcpsData(inputStream);
                                log.info("No. of lcps records {}", lcpsData.size());
                                lcpsRepository.deleteAll();
                                List<Lcps> lcpsList = lcpsData.stream().map(lcpsMapper::lcpsDataToLcps).collect(Collectors.toList());
                                lcpsRepository.saveAll(lcpsList);
                            } catch (IOException e) {
                                log.error("Could not load data {}", e.getMessage());
                            }
                        }
                );

    }

    private List<LcpsData> convertCsvDataToLcpsData(InputStream inputStream) {

        return convertCsvToList.execute(inputStream, LcpsData.class);

    }
}
