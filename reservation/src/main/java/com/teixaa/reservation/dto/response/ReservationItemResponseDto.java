package com.teixaa.reservation.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationItemResponseDto {

    private UUID id;

    private UUID sectorId;

    private UUID seatId;

    private Integer quantity;

    private BigDecimal unitPrice;

    private BigDecimal subtotal;
}