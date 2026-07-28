package com.teixaa.events.integration.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionPriceIntegrationResponse {

    private UUID sessionId;

    private List<SectorPriceIntegrationResponse> prices;
}