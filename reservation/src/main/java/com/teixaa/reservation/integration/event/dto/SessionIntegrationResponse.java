package com.teixaa.reservation.integration.event.dto;

import com.teixaa.reservation.integration.enums.SessionStatus;
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