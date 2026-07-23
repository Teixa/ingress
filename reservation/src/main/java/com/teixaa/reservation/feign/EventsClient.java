package com.teixaa.reservation.feign;

import com.teixaa.reservation.feign.dto.SessionFeignDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient("events")
public interface EventsClient {

    @GetMapping("/api/session/fetchSessionDetails/{eventId}/{sessionId}")
    SessionFeignDto fetchSessionDetails(
            @PathVariable UUID eventId,
            @PathVariable UUID sessionId);
}


