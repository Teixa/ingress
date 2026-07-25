package com.teixaa.reservation.integration.event.client;

import com.teixaa.reservation.integration.event.dto.SessionPriceResponse;
import com.teixaa.reservation.integration.event.dto.SessionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(
        name = "event-service",
        path = "/api/sessions"
)
public interface EventFeignClient {

    @GetMapping("/{sessionId}")
    SessionResponse getSession(
            @PathVariable UUID sessionId);

    @GetMapping("/{sessionId}/prices")
    SessionPriceResponse getPrices(
            @PathVariable UUID sessionId);
}