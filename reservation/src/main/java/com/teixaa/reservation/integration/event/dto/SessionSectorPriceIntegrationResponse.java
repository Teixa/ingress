package com.teixaa.reservation.integration.event.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionSectorPriceIntegrationResponse {

    private UUID sessionId;

    private UUID sectorId;

    private BigDecimal unitPrice;

}