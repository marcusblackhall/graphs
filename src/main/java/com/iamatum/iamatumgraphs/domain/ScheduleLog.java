package com.iamatum.iamatumgraphs.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleLog {

    @Id
    @GeneratedValue
    private UUID uuid;
    @NotNull
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ScheduleStatus scheduleStatus;
    private String info;

}
