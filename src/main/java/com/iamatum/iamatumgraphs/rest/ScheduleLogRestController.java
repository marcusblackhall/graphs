package com.iamatum.iamatumgraphs.rest;

import com.iamatum.iamatumgraphs.domain.ScheduleLog;
import com.iamatum.iamatumgraphs.services.ScheduleLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/log")
@RequiredArgsConstructor
public class ScheduleLogRestController {

    private final ScheduleLogService scheduleLogService;

    public List<ScheduleLog> scheduleLog() {
        return scheduleLogService.retrieveScheduleLog();
    }


}
