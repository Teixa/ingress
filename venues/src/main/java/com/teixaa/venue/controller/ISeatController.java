package com.teixaa.venue.controller;

import com.teixaa.venue.dto.request.CreateSeatRequestDto;
import com.teixaa.venue.dto.request.UpdateSeatRequestDto;
import com.teixaa.venue.dto.response.SeatResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
        name = "CRUD REST APIs for Seats",
        description = "CRUD REST APIs to CREATE, UPDATE, FETCH and DELETE seats"
)
public interface ISeatController {

    @PostMapping("/sectors/{sectorId}/seats")
    @ResponseStatus(HttpStatus.CREATED)
    SeatResponseDto create(
            @PathVariable UUID sectorId,
            @Valid @RequestBody CreateSeatRequestDto request);

    @GetMapping("/sectors/{sectorId}/seats")
    List<SeatResponseDto> findBySector(
            @PathVariable UUID sectorId);

    @GetMapping("/seats/{seatId}")
    SeatResponseDto findById(
            @PathVariable UUID seatId);

    @PutMapping("/seats/{seatId}")
    SeatResponseDto update(
            @PathVariable UUID seatId,
            @Valid @RequestBody UpdateSeatRequestDto request);

    @DeleteMapping("/seats/{seatId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(
            @PathVariable UUID seatId);

}