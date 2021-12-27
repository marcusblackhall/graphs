package com.iamatum.iamatumgraphs.repositories;

import com.iamatum.iamatumgraphs.domain.ScheduleLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ScheduleLogRepository extends JpaRepository<ScheduleLog, UUID> {

    public ScheduleLog findByUuid(UUID uuid);
    public List<ScheduleLog> findByOrderByStartTime();
}
