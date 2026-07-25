package com.teixaa.reservation.integration.event.client;

import com.teixaa.reservation.integration.event.dto.*;

import java.util.UUID;

public interface EventClient {

    SessionIntegrationResponse getSession(UUID sessionId);

    SessionPriceIntegrationResponse getPrices(UUID sessionId);

    SessionSectorPriceIntegrationResponse getSectorPrice(
            UUID sessionId,
            UUID sectorId);

}