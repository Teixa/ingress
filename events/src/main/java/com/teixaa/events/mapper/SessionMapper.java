package com.teixaa.events.mapper;

import com.teixaa.events.dto.request.CreateSessionRequestDto;
import com.teixaa.events.dto.response.SessionResponseDto;
import com.teixaa.events.entity.Session;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SessionMapper {

    Session toEntity(CreateSessionRequestDto request);

    @Mapping(target = "eventId", source = "event.id")
    @Mapping(target = "eventName", source = "event.name")
    SessionResponseDto toResponse(Session session);

}
