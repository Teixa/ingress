package com.teixaa.reservation.integration.event.client;

import com.teixaa.reservation.integration.event.dto.SessionPriceResponse;
import com.teixaa.reservation.integration.event.dto.SessionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EventClientImpl implements EventClient {

    private final EventFeignClient feignClient;

    @Override
    public SessionResponse getSession(UUID sessionId) {
        return feignClient.getSession(sessionId);
    }

    @Override
    public SessionPriceResponse getPrices(UUID sessionId) {
        return feignClient.getPrices(sessionId);
    }
}