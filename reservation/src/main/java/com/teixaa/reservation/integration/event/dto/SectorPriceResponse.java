package com.teixaa.reservation.integration.event.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SectorPriceResponse {

    private UUID sectorId;

    private BigDecimal price;
}