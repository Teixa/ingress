package com.teixaa.events.service;

import com.teixaa.events.dto.request.CreateEventRequestDto;
import com.teixaa.events.dto.response.EventResponseDto;

import java.util.UUID;

public interface IEventService {

    void saveEvent(CreateEventRequestDto createEventRequestDto);

    EventResponseDto findById(UUID id);
}
