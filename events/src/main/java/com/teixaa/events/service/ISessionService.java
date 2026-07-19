package com.teixaa.events.service;

import com.teixaa.events.dto.request.CreateSessionRequestDto;
import com.teixaa.events.dto.response.SessionResponseDto;
import com.teixaa.events.entity.Session;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

public interface ISessionService {

    SessionResponseDto create(UUID eventId,
                              CreateSessionRequestDto request);

    SessionResponseDto findByEventIdAndId(@PathVariable UUID eventId, @PathVariable UUID id);

    Session findSessionByEventIdAndId(@PathVariable UUID eventId, @PathVariable UUID id);
}
