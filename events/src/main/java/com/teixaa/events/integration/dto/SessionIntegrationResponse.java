package com.teixaa.events.integration.dto;

import com.teixaa.events.enums.SessionStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionIntegrationResponse {

    private UUID sessionId;

    private UUID eventId;

    private LocalDateTime salesStart;

    private LocalDateTime salesEnd;

    private SessionStatus status;
}