package com.teixaa.venues.mapper;

import com.teixaa.venues.dto.request.CreateVenueRequestDto;
import com.teixaa.venues.dto.request.UpdateVenueRequestDto;
import com.teixaa.venues.dto.response.VenueResponseDto;
import com.teixaa.venues.entity.Venue;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface VenueMapper {

    Venue toEntity(CreateVenueRequestDto request);

    VenueResponseDto toResponse(Venue venue);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    void updateEntity(UpdateVenueRequestDto request,
                      @MappingTarget Venue venue);
}