package com.teixaa.venues.mapper;

import com.teixaa.venues.dto.request.CreateSectorRequestDto;
import com.teixaa.venues.dto.request.UpdateSectorRequestDto;
import com.teixaa.venues.dto.response.SectorResponseDto;
import com.teixaa.venues.entity.Sector;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SectorMapper {

    Sector toEntity(CreateSectorRequestDto request);

    @Mapping(target = "venueId", source = "venue.id")
    @Mapping(target = "venueName", source = "venue.name")
    SectorResponseDto toResponse(Sector sector);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "venue", ignore = true)
    void updateEntity(
            UpdateSectorRequestDto request,
            @MappingTarget Sector sector);

}