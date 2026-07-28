package com.teixaa.events.service;

import com.teixaa.events.dto.request.CreateSessionRequestDto;
import com.teixaa.events.dto.request.UpdateSessionPricesRequestDto;
import com.teixaa.events.dto.response.SessionResponseDto;
import com.teixaa.events.entity.Session;

import java.util.UUID;

public interface ISessionService {

    SessionResponseDto create(UUID eventId,
                              CreateSessionRequestDto request);

    SessionResponseDto findById(UUID id);

    Session findSessionById(UUID id);

    SessionResponseDto updateSessionPrices(UUID sessionId, UpdateSessionPricesRequestDto request);
}
