package com.teixaa.events.service;

import com.teixaa.events.dto.request.CreateSessionRequestDto;
import com.teixaa.events.dto.response.SessionResponseDto;

import java.util.UUID;

public interface ISessionService {

    SessionResponseDto create(UUID eventId,
                              CreateSessionRequestDto request);
}
