package com.teixaa.reservation.integration.event.client;

import com.teixaa.reservation.exception.ResourceNotFoundException;
import com.teixaa.reservation.integration.event.dto.*;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EventClientImpl implements EventClient {

    private final EventFeignClient feignClient;

    @Override
    public SessionIntegrationResponse getSession(UUID sessionId) {

        try {
            return feignClient.getSession(sessionId);

        } catch (FeignException.NotFound ex) {

            throw new ResourceNotFoundException(
                    "Session",
                    "id",
                    sessionId.toString());

        }
    }

    @Override
    public SessionPriceIntegrationResponse getPrices(UUID sessionId) {

        try {
            return feignClient.getPrices(sessionId);

        } catch (FeignException.NotFound ex) {

            throw new ResourceNotFoundException(
                    "Session",
                    "id",
                    sessionId.toString());

        }
    }

    @Override
    public SessionSectorPriceIntegrationResponse getSectorPrice(
            UUID sessionId,
            UUID sectorId) {

        try {
            return feignClient.getSectorPrice(sessionId, sectorId);

        } catch (FeignException.NotFound ex) {

            throw new ResourceNotFoundException(
                    "Session",
                    "id",
                    sessionId.toString());

        }


    }
}