package com.teixaa.events.mapper;

import com.teixaa.events.dto.request.CreateEventRequestDto;
import com.teixaa.events.dto.response.EventResponseDto;
import com.teixaa.events.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "companyOrganizerId",
            source = "companyOrganizer.id")

    @Mapping(target = "companyOrganizerName",
            source = "companyOrganizer.companyName")
    EventResponseDto toResponse(Event event);

    Event toEntity(CreateEventRequestDto request);

}
