package com.teixaa.reservation.controller;

import com.teixaa.reservation.dto.request.CreateReservationRequestDto;
import com.teixaa.reservation.dto.response.ErrorResponseDto;
import com.teixaa.reservation.dto.response.ReservationResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(
        name = "Reservation APIs",
        description = "APIs to manage carts and reservations"
)
@RestController
@RequestMapping(path = "/api/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
public interface IReservationController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ReservationResponseDto create(
            @Valid @RequestBody CreateReservationRequestDto request);

    @GetMapping("/{id}")
    ReservationResponseDto findById(@PathVariable UUID id);

    @GetMapping("/customer/{customerId}")
    List<ReservationResponseDto> findByCustomer(
            @PathVariable UUID customerId);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void cancel(@PathVariable UUID id);

}