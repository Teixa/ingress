package com.teixaa.events.service.client;

import com.teixaa.events.dto.feign.venues.VenueResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient("venues")
public interface VenuesFeignClient {
    @GetMapping(value = "/api/venues/{venueId}", consumes = "application/json")
    VenueResponseDto findById(
            @PathVariable UUID venueId);
}
