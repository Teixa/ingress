package com.teixaa.events.service.impl;

import com.teixaa.events.dto.request.CreateSessionRequestDto;
import com.teixaa.events.dto.response.SessionResponseDto;
import com.teixaa.events.entity.Event;
import com.teixaa.events.entity.Session;
import com.teixaa.events.enums.SessionStatus;
import com.teixaa.events.mapper.SessionMapper;
import com.teixaa.events.repository.SessionRepository;
import com.teixaa.events.service.IEventService;
import com.teixaa.events.service.ISessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

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

        session.setEvent(event);

        session.setStatus(SessionStatus.SCHEDULED);

        session = sessionRepository.save(session);

        return sessionMapper.toResponse(session);
    }

}
