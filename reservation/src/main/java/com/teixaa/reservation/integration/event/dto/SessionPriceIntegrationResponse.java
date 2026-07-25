package com.teixaa.reservation.integration.event.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
public class SessionPriceIntegrationResponse {

    private UUID sessionId;

    private List<SectorPriceIntegrationResponse> prices;
}