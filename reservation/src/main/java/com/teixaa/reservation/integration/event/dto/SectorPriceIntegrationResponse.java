package com.teixaa.reservation.integration.event.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
public class SectorPriceIntegrationResponse {

    private UUID sectorId;

    private BigDecimal price;
}