package com.teixaa.reservation.feign;

import com.teixaa.reservation.feign.dto.SeatFeignDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient("venues")
public interface VenuesClient {

    @GetMapping("/api/seats/{seatId}")
    SeatFeignDto getSeat(@PathVariable UUID seatId);
}


