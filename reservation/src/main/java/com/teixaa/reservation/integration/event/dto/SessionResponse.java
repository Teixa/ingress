package com.teixaa.reservation.integration.event.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessionResponse {

    private UUID id;

    private UUID eventId;

    private LocalDateTime dateTime;

    private LocalDateTime salesStart;

    private LocalDateTime salesEnd;
}