package com.teixaa.events.integration.dto;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public class SessionPriceIntegrationResponse {

    private UUID sessionId;

    private List<SectorPriceIntegrationResponse> prices;
}