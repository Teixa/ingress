package com.teixaa.venue.service;

import com.teixaa.venue.dto.request.CreateVenueRequestDto;
import com.teixaa.venue.dto.request.UpdateVenueRequestDto;
import com.teixaa.venue.dto.response.VenueResponseDto;
import com.teixaa.venue.entity.Venue;

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