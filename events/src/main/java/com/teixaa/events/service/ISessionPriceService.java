package com.teixaa.events.service;

import com.teixaa.events.entity.SessionPrice;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ISessionPriceService {

    SessionPrice getSectorPrice(UUID sessionId, UUID sectorId);
}
