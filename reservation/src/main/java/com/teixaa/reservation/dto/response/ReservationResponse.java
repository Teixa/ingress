package com.teixaa.reservation.dto.response;

import com.teixaa.reservation.enums.ReservationStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationResponse {

    private UUID id;

    private UUID customerId;

    private UUID eventId;

    private UUID sessionId;

    private ReservationStatus status;

    private BigDecimal totalAmount;

    private LocalDateTime expiresAt;

    private List<ReservationItemResponse> items;
}