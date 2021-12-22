package com.iamatum.iamatumgraphs.requests;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.netty.http.client.HttpClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Returning LCPS data tests")
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ReturnLcpsDataTest {

    @Value("${lcps.url}")
    String lcpsUrl;

    @Test
    void shouldReturnLcpsData() throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        WebClient webClient = WebClient.builder()
                .baseUrl(lcpsUrl)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create().wiretap(true)))
                .build();

        Flux<DataBuffer> dataBufferFlux = webClient.get()
                .retrieve()
                .bodyToFlux(DataBuffer.class);

        Path outPath = Paths.get("lcps.csv");

        Disposable retrieved_all_data_from_lcps = DataBufferUtils.write(dataBufferFlux, outPath, StandardOpenOption.CREATE)

                .subscribe(unused -> {
                        },
                        throwable -> log.info("thrown error {}", throwable.getMessage()),
                        () -> {
                            countDownLatch.countDown();
                            log.info("Retrieved all data from lcps");
                            try {
                                long size = Files.size(outPath);
                                log.info("No. of bytes {}",size);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                );


        countDownLatch.await(1, TimeUnit.SECONDS);
        assertThat(countDownLatch.getCount()).isEqualTo(0);
        assertTrue(Files.exists(outPath));

    }


}
