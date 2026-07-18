package com.teixaa.events.mapper;

import com.teixaa.events.dto.request.CreateEventRequestDto;
import com.teixaa.events.dto.request.UpdateEventRequestDto;
import com.teixaa.events.dto.response.EventResponseDto;
import com.teixaa.events.entity.Event;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "companyOrganizerId",
            source = "companyOrganizer.id")
    @Mapping(target = "companyOrganizerName",
            source = "companyOrganizer.companyName")
    EventResponseDto toResponse(Event event);

    Event toEntity(CreateEventRequestDto request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "companyOrganizer", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "sessions", ignore = true)
    void updateEntity(UpdateEventRequestDto request,
                      @MappingTarget Event event);

}
