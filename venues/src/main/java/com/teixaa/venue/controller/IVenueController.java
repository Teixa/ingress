package com.teixaa.venue.controller;

import com.teixaa.venue.dto.request.CreateVenueRequestDto;
import com.teixaa.venue.dto.request.UpdateVenueRequestDto;
import com.teixaa.venue.dto.response.ErrorResponseDto;
import com.teixaa.venue.dto.response.VenueResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(
        name = "CRUD REST APIs for Venues",
        description = "CRUD REST APIs to CREATE, UPDATE, FETCH and DELETE venues"
)
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public interface IVenueController {

    @Operation(
            summary = "Create Venue REST API",
            description = "REST API to create a Venue based on a request"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED",
                    content = {@Content (mediaType = "application/json",schema = @Schema(implementation =  CreateVenueRequestDto.class))}
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/venues/create")
    @ResponseStatus(HttpStatus.CREATED)
    VenueResponseDto create(
            @Valid @RequestBody CreateVenueRequestDto request);

    @Operation(summary = "Find Venue By Id")
    @GetMapping("/venues/{venueId}")
    VenueResponseDto findById(
            @PathVariable UUID venueId);

    @Operation(summary = "Find All Venues")
    @GetMapping("/venues")
    List<VenueResponseDto> findAll();

    @Operation(summary = "Update Venue")
    @PutMapping("/venues/{venueId}")
    VenueResponseDto update(
            @PathVariable UUID venueId,
            @Valid @RequestBody UpdateVenueRequestDto request);

    @Operation(summary = "Delete Venue")
    @DeleteMapping("/venues/{venueId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(
            @PathVariable UUID venueId);
}