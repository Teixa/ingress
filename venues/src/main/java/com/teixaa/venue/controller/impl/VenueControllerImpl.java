package com.teixaa.venue.controller.impl;


import com.teixaa.venue.controller.IVenueController;
import com.teixaa.venue.dto.request.CreateVenueRequestDto;
import com.teixaa.venue.dto.request.UpdateVenueRequestDto;
import com.teixaa.venue.dto.response.VenueResponseDto;
import com.teixaa.venue.service.IVenueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequiredArgsConstructor
public class VenueControllerImpl implements IVenueController {

    private final IVenueService venueService;

    @Override
    public VenueResponseDto create(
            @Valid @RequestBody CreateVenueRequestDto request) {

        return venueService.create(request);
    }

    @Override
    public VenueResponseDto findById(UUID venueId) {

        return venueService.findById(venueId);
    }

    @Override
    public List<VenueResponseDto> findAll() {

        return venueService.findAll();
    }

    @Override
    public VenueResponseDto update(
            UUID venueId,
            UpdateVenueRequestDto request) {

        return venueService.update(venueId, request);
    }

    @Override
    public void delete(UUID venueId) {

        venueService.delete(venueId);
    }
}