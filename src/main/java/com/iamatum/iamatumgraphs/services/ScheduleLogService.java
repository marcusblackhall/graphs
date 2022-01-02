package com.iamatum.iamatumgraphs.services;

import com.iamatum.iamatumgraphs.domain.ScheduleLog;
import com.iamatum.iamatumgraphs.repositories.ScheduleLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ScheduleLogService {

    final ScheduleLogRepository scheduleLogRepository;

    public List<ScheduleLog> retrieveScheduleLog() {
        return scheduleLogRepository.findByOrderByStartTime();
    }
}
