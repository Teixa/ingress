package com.teixaa.events.service.impl;

import com.teixaa.events.entity.SessionPrice;
import com.teixaa.events.repository.SessionRepository;
import com.teixaa.events.service.ISessionPriceService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionPriceServiceImpl implements ISessionPriceService {

    private SessionRepository sessionRepository;

    @Override
    public SessionPrice getSectorPrice(UUID sessionId, UUID sectorId) {

        return null; // Replace with actual implementation
    }
}
