package com.iamatum.iamatumgraphs.scheduled;

import com.iamatum.iamatumgraphs.FetchData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class ScheduleFetchLcps {

    private final FetchData fetchData;

    @Scheduled(cron = "${schedule.lcps}")
    public void executeCovidLoader() throws IOException {
        fetchData.retrieveLcpsFigures();
    }


}
