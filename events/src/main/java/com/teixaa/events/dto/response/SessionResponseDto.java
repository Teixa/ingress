package com.teixaa.events.dto.response;


import com.teixaa.events.enums.SessionStatus;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionResponseDto {
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

