package com.teixaa.events.controller;

import com.teixaa.events.dto.request.CreateSessionRequestDto;
import com.teixaa.events.dto.request.UpdateSessionPricesRequestDto;
import com.teixaa.events.dto.response.ErrorResponseDto;
import com.teixaa.events.dto.response.EventResponseDto;
import com.teixaa.events.dto.response.SessionResponseDto;
import com.teixaa.events.integration.dto.SessionSectorPriceIntegrationResponse;
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

import java.util.UUID;

@Tag(
        name = "CRUD REST APIs for Events in Ingress",
        description = "CRUD REST APIs in Ingress to CREATE, UPDATE, FETCH AND DELETE Events details"
)
@RestController()
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public interface ISessionController {

    @Operation(
            summary = "Create Session REST API",
            description = "REST API to create a Session"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = SessionResponseDto.class))}
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
    @PostMapping("/events/{eventId}/sessions")
    @ResponseStatus(HttpStatus.CREATED)
    SessionResponseDto create(
            @PathVariable UUID eventId,
            @Valid @RequestBody CreateSessionRequestDto request);

    @Operation(
            summary = "Find Session REST API",
            description = "REST API to fetch details about a Session"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = SessionResponseDto.class))}
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
    @GetMapping("/events/{eventId}/sessions/{id}")
    @ResponseStatus(HttpStatus.OK)
    SessionResponseDto fetchSessionDetails(@PathVariable UUID eventId, @PathVariable UUID id);

    @PutMapping("/sessions/{sessionId}/prices")
    @ResponseStatus(HttpStatus.OK)
    SessionResponseDto updateSessionPrices(@PathVariable UUID sessionId, @Valid @RequestBody UpdateSessionPricesRequestDto request);
}
