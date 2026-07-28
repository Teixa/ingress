package com.teixaa.events.controller.impl;

import com.teixaa.events.controller.ISessionController;
import com.teixaa.events.dto.request.CreateSessionRequestDto;
import com.teixaa.events.dto.request.UpdateSessionPricesRequestDto;
import com.teixaa.events.dto.response.SessionResponseDto;
import com.teixaa.events.service.ISessionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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

    @Override
    public SessionResponseDto getSession(@PathVariable UUID id) {

        return sessionService.findById(id);
    }

    @Override
    public SessionResponseDto updateSessionPrices(UUID sessionId, UpdateSessionPricesRequestDto request) {
        return sessionService.updateSessionPrices(sessionId, request);
    }


}
