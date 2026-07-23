package com.teixaa.reservation.feign.dto;

import com.teixaa.reservation.feign.enums.SessionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionFeignDto {
    private UUID id;

    private UUID eventId;

    private String eventName;

    private LocalDateTime dateTime;

    private LocalDateTime salesStart;

    private LocalDateTime salesEnd;

    private LocalDateTime gatesOpenAt;

    private Duration duration;

    private SessionStatus status;

    private String notes;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;
}

