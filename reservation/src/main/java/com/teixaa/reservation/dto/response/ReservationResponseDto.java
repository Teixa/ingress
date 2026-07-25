package com.teixaa.reservation.dto.response;

import com.teixaa.reservation.enums.ReservationStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationResponseDto {

    private UUID id;

    private UUID customerId;

    private UUID eventId;

    private UUID sessionId;

    private ReservationStatus status;

    private BigDecimal totalAmount;

    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    private LocalDateTime cancelledAt;

    private List<ReservationItemResponseDto> items;
}