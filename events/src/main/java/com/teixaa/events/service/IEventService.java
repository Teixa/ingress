package com.teixaa.events.service;

import com.teixaa.events.dto.request.CreateEventRequestDto;
import com.teixaa.events.dto.request.UpdateEventRequestDto;
import com.teixaa.events.dto.response.EventResponseDto;
import com.teixaa.events.entity.Event;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface IEventService {

    EventResponseDto saveEvent(CreateEventRequestDto createEventRequestDto);

    Event findEntityById(UUID id);

    @Transactional(readOnly = true)
    EventResponseDto findById(UUID id);

    @Transactional
    EventResponseDto update(UUID eventId, UpdateEventRequestDto request);
}
