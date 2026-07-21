package com.teixaa.venue.mapper;

import com.teixaa.venue.dto.request.CreateVenueRequestDto;
import com.teixaa.venue.dto.request.UpdateVenueRequestDto;
import com.teixaa.venue.dto.response.VenueResponseDto;
import com.teixaa.venue.entity.Venue;
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