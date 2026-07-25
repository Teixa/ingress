package com.teixaa.events.service;

import com.teixaa.events.dto.request.CreateSessionRequestDto;
import com.teixaa.events.dto.request.UpdateSessionPricesRequestDto;
import com.teixaa.events.dto.response.SessionResponseDto;
import com.teixaa.events.entity.Session;
import com.teixaa.events.integration.dto.SessionSectorPriceIntegrationResponse;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

public interface ISessionService {

    SessionResponseDto create(UUID eventId,
                              CreateSessionRequestDto request);

    SessionResponseDto findByEventIdAndId( UUID eventId, UUID id);

    Session findSessionByEventIdAndId(UUID eventId, UUID id);

    SessionResponseDto updateSessionPrices(UUID sessionId, UpdateSessionPricesRequestDto request);
}
