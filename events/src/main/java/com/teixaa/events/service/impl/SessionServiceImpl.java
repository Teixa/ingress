package com.teixaa.events.service.impl;

import com.teixaa.events.dto.request.CreateSessionPriceRequestDto;
import com.teixaa.events.dto.request.CreateSessionRequestDto;
import com.teixaa.events.dto.request.UpdateSessionPricesRequestDto;
import com.teixaa.events.dto.response.SessionResponseDto;
import com.teixaa.events.entity.Event;
import com.teixaa.events.entity.Session;
import com.teixaa.events.entity.SessionPrice;
import com.teixaa.events.enums.SessionStatus;
import com.teixaa.events.exception.ResourceException;
import com.teixaa.events.exception.ResourceNotFoundException;
import com.teixaa.events.integration.dto.SessionSectorPriceIntegrationResponse;
import com.teixaa.events.mapper.SessionMapper;
import com.teixaa.events.repository.SessionRepository;
import com.teixaa.events.service.IEventService;
import com.teixaa.events.service.ISessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class SessionServiceImpl implements ISessionService {

    private final SessionRepository sessionRepository;
    private final IEventService eventService;
    private final SessionMapper sessionMapper;

    @Override
    public SessionResponseDto create(UUID eventId,
                                     CreateSessionRequestDto request) {

        Event event = eventService.findEntityById(eventId);

        Session session = sessionMapper.toEntity(request);
        session.setPrices(new ArrayList<>());

        session.setEvent(event);

        session.setStatus(SessionStatus.SCHEDULED);

        Set<UUID> sectors = new HashSet<>();

        if(Objects.nonNull(request.getPrices())) {
            for (CreateSessionPriceRequestDto dto : request.getPrices()) {


                SessionPrice sessionPrice = SessionPrice.builder()
                        .session(session)
                        .sectorId(dto.getSectorId())
                        .unitPrice(dto.getUnitPrice())
                        .build();

                if (!sectors.add(sessionPrice.getSectorId())) {
                    throw new ResourceException(
                            "Duplicated sector price.");
                }

                session.getPrices().add(sessionPrice);
            }
        }

        session = sessionRepository.save(session);

        return sessionMapper.toResponse(session);
    }

    @Override
    public Session findSessionByEventIdAndId(UUID eventId, UUID id) {
        return sessionRepository.findByEventIdAndId(eventId, id).orElseThrow(
                () -> new ResourceNotFoundException("Session", "id", id.toString())
        );
    }

    @Override
    public SessionResponseDto findByEventIdAndId(UUID eventId, UUID id) {
        return sessionMapper.toResponse(findSessionByEventIdAndId(eventId, id));
    }

    @Override
    @Transactional
    public SessionResponseDto updateSessionPrices(
            UUID sessionId,
            UpdateSessionPricesRequestDto request) {

        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException("Session", "id", sessionId.toString()));

        session.getPrices().clear();

        for (CreateSessionPriceRequestDto dto : request.getPrices()) {

            SessionPrice sessionPrice = SessionPrice.builder()
                    .session(session)
                    .sectorId(dto.getSectorId())
                    .unitPrice(dto.getUnitPrice())
                    .build();

            session.getPrices().add(sessionPrice);
        }
        return sessionMapper.toResponse(sessionRepository.save(session));
    }


}
