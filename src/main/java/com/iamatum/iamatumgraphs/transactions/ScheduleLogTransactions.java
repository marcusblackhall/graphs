package com.iamatum.iamatumgraphs.transactions;

import com.iamatum.iamatumgraphs.domain.ScheduleLog;
import com.iamatum.iamatumgraphs.domain.ScheduleStatus;
import com.iamatum.iamatumgraphs.repositories.ScheduleLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Transactional
@Component
@RequiredArgsConstructor
public class ScheduleLogTransactions {

    private final ScheduleLogRepository scheduleLogRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ScheduleLog saveScheduleLog(String name){

        ScheduleLog scheduleLog = ScheduleLog.builder()
                .name(name)
                .startTime(LocalDateTime.now())
                .build();
        return scheduleLogRepository.save(scheduleLog);

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateError(UUID uuid) {
        ScheduleLog byUUid = scheduleLogRepository.findByUuid(uuid);
        byUUid.setScheduleStatus(ScheduleStatus.FAILED);
        byUUid.setEndTime(LocalDateTime.now());
    }

    public void updateSuccess(UUID uuid,String msg) {

        ScheduleLog byUUid = scheduleLogRepository.findByUuid(uuid);
        byUUid.setScheduleStatus(ScheduleStatus.SUCCESS);
        byUUid.setEndTime(LocalDateTime.now());
        byUUid.setInfo(msg);
    }
}
