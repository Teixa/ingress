package com.teixaa.venues.service;

import com.teixaa.venues.dto.request.CreateVenueRequestDto;
import com.teixaa.venues.dto.request.UpdateVenueRequestDto;
import com.teixaa.venues.dto.response.VenueResponseDto;
import com.teixaa.venues.entity.Venue;

import java.util.List;
import java.util.UUID;

public interface IVenueService {

    VenueResponseDto create(CreateVenueRequestDto request);

    VenueResponseDto findById(UUID venueId);

    List<VenueResponseDto> findAll();

    VenueResponseDto update(UUID venueId, UpdateVenueRequestDto request);

    void delete(UUID venueId);

    Venue getByIdOrThrow(UUID venueId);
}