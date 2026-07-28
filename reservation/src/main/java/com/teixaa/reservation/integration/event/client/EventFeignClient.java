package com.teixaa.reservation.integration.event.client;


import com.teixaa.reservation.integration.event.dto.SessionIntegrationResponse;
import com.teixaa.reservation.integration.event.dto.SessionPriceIntegrationResponse;
import com.teixaa.reservation.integration.event.dto.SessionSectorPriceIntegrationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(
        name = "event",
        path = "/api/integration/sessions"
)
public interface EventFeignClient {

    @GetMapping("/{sessionId}")
    SessionIntegrationResponse getSession(
            @PathVariable UUID sessionId);

    @GetMapping("/{sessionId}/prices")
    SessionPriceIntegrationResponse getPrices(
            @PathVariable UUID sessionId);

    @GetMapping("/{sessionId}/prices/{sectorId}")
    SessionSectorPriceIntegrationResponse getSectorPrice(
            @PathVariable UUID sessionId,
            @PathVariable UUID sectorId);

}