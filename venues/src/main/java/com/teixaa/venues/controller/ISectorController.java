package com.teixaa.venues.controller;

import com.teixaa.venues.dto.request.CreateSectorRequestDto;
import com.teixaa.venues.dto.request.UpdateSectorRequestDto;
import com.teixaa.venues.dto.response.SectorResponseDto;
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
        name = "CRUD REST APIs for Sectors",
        description = "CRUD REST APIs to CREATE, UPDATE, FETCH and DELETE sectors"
)
public interface ISectorController {

    @PostMapping("/venues/{venueId}/sectors")
    @ResponseStatus(HttpStatus.CREATED)
    SectorResponseDto create(
            @PathVariable UUID venueId,
            @Valid @RequestBody CreateSectorRequestDto request);

    @GetMapping("/venues/{venueId}/sectors")
    List<SectorResponseDto> findByVenue(
            @PathVariable UUID venueId);

    @GetMapping("/sectors/{sectorId}")
    SectorResponseDto findById(
            @PathVariable UUID sectorId);

    @PutMapping("/sectors/{sectorId}")
    SectorResponseDto update(
            @PathVariable UUID sectorId,
            @Valid @RequestBody UpdateSectorRequestDto request);

    @DeleteMapping("/sectors/{sectorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(
            @PathVariable UUID sectorId);
}
