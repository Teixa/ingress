package com.teixaa.events.mapper;

import com.teixaa.events.dto.request.CreateSessionRequestDto;
import com.teixaa.events.dto.request.UpdateSessionPricesRequestDto;
import com.teixaa.events.dto.response.SessionPriceResponseDto;
import com.teixaa.events.dto.response.SessionResponseDto;
import com.teixaa.events.entity.Session;
import com.teixaa.events.entity.SessionPrice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SessionMapper {

    Session toEntity(CreateSessionRequestDto request);

    @Mapping(target = "eventId", source = "event.id")
    @Mapping(target = "eventName", source = "event.name")
    @Mapping(target = "prices", source = "prices")
    SessionResponseDto toResponse(Session session);

    SessionPriceResponseDto toResponse(SessionPrice price);

    List<SessionPriceResponseDto> toResponse(List<SessionPrice> prices);

}
