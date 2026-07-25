package com.teixaa.events.service;

import com.teixaa.events.integration.dto.SessionIntegrationResponse;
import com.teixaa.events.integration.dto.SessionPriceIntegrationResponse;

import java.util.UUID;


public interface ISessionIntegrationService {

    SessionIntegrationResponse getSession(UUID sessionId);

    SessionPriceIntegrationResponse getPrices(UUID sessionId);

}