package com.teixaa.events.service.impl;

import com.teixaa.events.entity.Session;
import com.teixaa.events.entity.SessionPrice;
import com.teixaa.events.exception.ResourceNotFoundException;
import com.teixaa.events.integration.dto.SessionIntegrationResponse;
import com.teixaa.events.integration.dto.SessionPriceIntegrationResponse;
import com.teixaa.events.mapper.SessionIntegrationMapper;
import com.teixaa.events.repository.SessionPriceRepository;
import com.teixaa.events.repository.SessionRepository;
import com.teixaa.events.service.ISessionIntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SessionIntegrationServiceImpl
        implements ISessionIntegrationService {

    private final SessionRepository sessionRepository;

    private final SessionPriceRepository sessionPriceRepository;

    private final SessionIntegrationMapper mapper;

    @Override
    public SessionIntegrationResponse getSession(UUID sessionId) {

        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Session", "sessionId", sessionId.toString()));

        return mapper.toIntegration(session);
    }

    @Override
    public SessionPriceIntegrationResponse getPrices(UUID sessionId) {

        List<SessionPrice> prices =
                sessionPriceRepository.findAllBySessionId(sessionId);

        return mapper.toIntegration(sessionId, prices);
    }

}