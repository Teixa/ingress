package com.teixaa.events.service;

import com.teixaa.events.dto.request.CreateEventRequestDto;

public interface IEventService {
    void saveEvent(CreateEventRequestDto createEventRequestDto);
}
