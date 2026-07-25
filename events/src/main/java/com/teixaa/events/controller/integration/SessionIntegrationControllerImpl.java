package com.teixaa.events.controller.integration;

import com.teixaa.events.controller.api.ISessionIntegrationController;
import com.teixaa.events.integration.dto.SessionIntegrationResponse;
import com.teixaa.events.integration.dto.SessionPriceIntegrationResponse;
import com.teixaa.events.integration.dto.SessionSectorPriceIntegrationResponse;
import com.teixaa.events.service.ISessionIntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class SessionIntegrationControllerImpl
        implements ISessionIntegrationController {

    private final ISessionIntegrationService sessionService;

    @Override
    public SessionIntegrationResponse getSession(UUID sessionId) {
        return sessionService.getSession(sessionId);
    }

    @Override
    public SessionPriceIntegrationResponse getPrices(UUID sessionId) {
        return sessionService.getPrices(sessionId);
    }

    @Override
    public SessionSectorPriceIntegrationResponse getSectorPrice(UUID sessionId, UUID sectorId) {
        return sessionService.getSectorPrice(sessionId, sectorId);
    }

}