package com.teixaa.reservation.integration.event.client;

import com.teixaa.reservation.integration.event.dto.SessionPriceResponse;
import com.teixaa.reservation.integration.event.dto.SessionResponse;

import java.util.UUID;

public interface EventClient {

    SessionResponse getSession(UUID sessionId);

    SessionPriceResponse getPrices(UUID sessionId);
}