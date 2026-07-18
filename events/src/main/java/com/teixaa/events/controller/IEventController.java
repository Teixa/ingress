package com.teixaa.events.controller;

import com.teixaa.events.dto.request.CreateEventRequestDto;
import com.teixaa.events.dto.request.UpdateEventRequestDto;
import com.teixaa.events.dto.response.ErrorResponseDto;
import com.teixaa.events.dto.ResponseDto;
import com.teixaa.events.dto.response.EventResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(
        name = "CRUD REST APIs for Events in Ingress",
        description = "CRUD REST APIs in Ingress to CREATE, UPDATE, FETCH AND DELETE Events details"
)
@RestController()
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public interface IEventController {



    @Operation(
            summary = "Create Event REST API",
            description = "REST API to create an Event"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = EventResponseDto.class))}
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
    @PostMapping(path = "createEvent")
    public ResponseEntity<EventResponseDto> createEvent (@Valid @RequestBody CreateEventRequestDto createEventRequestDto);


    @Operation(
            summary = "Fetch Event REST API",
            description = "REST API to find an Event passing UUID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = EventResponseDto.class))}
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
    @GetMapping(path = "fetchEvent")
    public ResponseEntity<EventResponseDto> findEventById(@RequestParam UUID id);

    @Operation(
            summary = "Update Event Details REST API",
            description = "REST API to update Event details based on a uuid"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
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
    @PutMapping("/updateEvent/{eventId}")
    public EventResponseDto update(
            @PathVariable UUID eventId,
            @Valid @RequestBody UpdateEventRequestDto request);
}
