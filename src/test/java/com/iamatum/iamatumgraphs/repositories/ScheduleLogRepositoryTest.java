package com.iamatum.iamatumgraphs.repositories;

import com.iamatum.iamatumgraphs.domain.ScheduleLog;
import com.iamatum.iamatumgraphs.domain.ScheduleStatus;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class ScheduleLogRepositoryTest {

    @Autowired
    private ScheduleLogRepository scheduleLogRepository;

    @Test
    void shouldAddScheduleLog() {
        ScheduleLog scheduleLog =
                ScheduleLog.builder()
                        .name("Load LCPS data")
                        .startTime(LocalDateTime.now())
                        .endTime(LocalDateTime.now())
                        .scheduleStatus(ScheduleStatus.SUCCESS)
                        .build();

        scheduleLogRepository.save(scheduleLog);

        List<ScheduleLog> scheduleLogs = scheduleLogRepository.findAll();
        assertThat(scheduleLogs.size()).isEqualTo(1);

    }
}