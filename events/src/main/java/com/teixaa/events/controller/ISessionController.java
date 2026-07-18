package com.teixaa.events.controller;

import com.teixaa.events.dto.request.CreateSessionRequestDto;
import com.teixaa.events.dto.response.SessionResponseDto;
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

    @PostMapping("/{eventId}/sessions")
    @ResponseStatus(HttpStatus.CREATED)
    SessionResponseDto create(
            @PathVariable UUID eventId,
            @Valid @RequestBody CreateSessionRequestDto request);
}
