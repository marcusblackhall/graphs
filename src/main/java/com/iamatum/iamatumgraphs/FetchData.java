package com.iamatum.iamatumgraphs;

import com.iamatum.iamatumgraphs.converters.ConvertCsvToList;
import com.iamatum.iamatumgraphs.domain.ScheduleLog;
import com.iamatum.iamatumgraphs.mappers.LcpsMapper;
import com.iamatum.iamatumgraphs.model.LcpsData;
import com.iamatum.iamatumgraphs.services.LcpsService;
import com.iamatum.iamatumgraphs.transactions.ScheduleLogTransactions;
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

@Component
@Slf4j
@RequiredArgsConstructor
public class FetchData {

    @Value("${schedule.lcps.url}")
    private String lcpsUrl;

    private final ConvertCsvToList<LcpsData> convertCsvToList;
    private final LcpsService lcpsService;
    private final LcpsMapper lcpsMapper;
    private final WebClient webClient;
    private final ScheduleLogTransactions scheduleLogTransactions;

    public void retrieveLcpsFigures(String name) throws IOException {
        log.info("Retrieving data for {}", name);

        ScheduleLog scheduleLog = scheduleLogTransactions.saveScheduleLog(name);

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
                                int noRecords = lcpsService.loadLcpsData(lcpsData);
                                scheduleLogTransactions.updateSuccess(scheduleLog.getUuid(), String.format("No. loaded %s", noRecords));
                            } catch (IOException e) {
                                scheduleLogTransactions.updateError(scheduleLog.getUuid());
                                log.error("Could not load data {}", e.getMessage());
                            }
                        }
                );

    }

    private List<LcpsData> convertCsvDataToLcpsData(InputStream inputStream) {

        return convertCsvToList.execute(inputStream, LcpsData.class);

    }

}
