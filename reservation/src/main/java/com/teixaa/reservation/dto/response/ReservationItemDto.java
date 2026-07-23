package com.teixaa.reservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationItemDto {
    private UUID id;
    private UUID eventId;
    private UUID sessionId;
    private UUID seatId;
    private BigDecimal price;
    private Integer quantity;
}

