package com.teixaa.events.controller.impl;

import com.teixaa.events.controller.ISessionController;
import com.teixaa.events.dto.request.CreateSessionRequestDto;
import com.teixaa.events.dto.response.SessionResponseDto;
import com.teixaa.events.service.ISessionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Validated
@AllArgsConstructor
public class SessionControllerImpl implements ISessionController {

    private final ISessionService sessionService;

    @Override
    public SessionResponseDto create(
            @PathVariable UUID eventId,
            @Valid @RequestBody CreateSessionRequestDto request) {

        return sessionService.create(eventId, request);
    }

}
