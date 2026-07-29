package com.teixaa.reservation.integration.venue;

import com.teixaa.reservation.integration.venue.dto.ValidateSeatsRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "venues",
        path = "/api/integration/seats"
)
public interface VenueFeignClient {

    @PostMapping("/validate")
    void validateSeats(
            @RequestBody ValidateSeatsRequest request);

}