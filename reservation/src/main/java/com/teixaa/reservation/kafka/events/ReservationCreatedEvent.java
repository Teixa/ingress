package com.teixaa.reservation.kafka.events;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationCreatedEvent {

    private UUID reservationId;

    private UUID customerId;

    private UUID eventId;

    private UUID sessionId;

    private BigDecimal totalAmount;

    private LocalDateTime createdAt;

}