package com.teixaa.venues.mapper;

import com.teixaa.venues.dto.request.CreateSeatRequestDto;
import com.teixaa.venues.dto.request.UpdateSeatRequestDto;
import com.teixaa.venues.dto.response.SeatResponseDto;
import com.teixaa.venues.entity.Seat;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SeatMapper {

    Seat toEntity(CreateSeatRequestDto request);

    @Mapping(target = "sectorId", source = "sector.id")
    @Mapping(target = "sectorName", source = "sector.name")
    @Mapping(target = "venueId", source = "sector.venue.id")
    @Mapping(target = "venueName", source = "sector.venue.name")
    SeatResponseDto toResponse(Seat seat);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sector", ignore = true)
    @Mapping(target = "active", ignore = true)
    void updateEntity(
            UpdateSeatRequestDto request,
            @MappingTarget Seat seat);
}