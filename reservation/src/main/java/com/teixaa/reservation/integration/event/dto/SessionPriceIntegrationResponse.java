package com.teixaa.reservation.integration.event.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionPriceIntegrationResponse {

    private UUID sessionId;

    private List<SectorPriceIntegrationResponse> prices;

}