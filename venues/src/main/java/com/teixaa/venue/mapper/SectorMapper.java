package com.teixaa.venue.mapper;

import com.teixaa.venue.dto.request.CreateSectorRequestDto;
import com.teixaa.venue.dto.request.UpdateSectorRequestDto;
import com.teixaa.venue.dto.response.SectorResponseDto;
import com.teixaa.venue.entity.Sector;
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