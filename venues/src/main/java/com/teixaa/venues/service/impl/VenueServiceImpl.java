package com.teixaa.venues.service.impl;

import com.teixaa.venues.dto.request.CreateVenueRequestDto;
import com.teixaa.venues.dto.request.UpdateVenueRequestDto;
import com.teixaa.venues.dto.response.VenueResponseDto;
import com.teixaa.venues.entity.Venue;
import com.teixaa.venues.exception.ResourceNotFoundException;
import com.teixaa.venues.mapper.VenueMapper;
import com.teixaa.venues.repository.VenueRepository;
import com.teixaa.venues.service.IVenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class VenueServiceImpl implements IVenueService {

    private final VenueRepository venueRepository;
    private final VenueMapper venueMapper;

    @Override
    public VenueResponseDto create(CreateVenueRequestDto request) {

        Venue venue = venueMapper.toEntity(request);

        venue.setActive(true);

        Venue savedVenue = venueRepository.save(venue);

        return venueMapper.toResponse(savedVenue);
    }

    @Override
    @Transactional(readOnly = true)
    public VenueResponseDto findById(UUID venueId) {

        return venueMapper.toResponse(getByIdOrThrow(venueId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<VenueResponseDto> findAll() {

        return venueRepository.findAllByActiveTrue()
                .stream()
                .map(venueMapper::toResponse)
                .toList();
    }

    @Override
    public VenueResponseDto update(UUID venueId,
                                   UpdateVenueRequestDto request) {

        Venue venue = getByIdOrThrow(venueId);

        venueMapper.updateEntity(request, venue);

        Venue updatedVenue = venueRepository.save(venue);

        return venueMapper.toResponse(updatedVenue);
    }

    @Override
    public void delete(UUID venueId) {

        Venue venue = getByIdOrThrow(venueId);

        venue.setActive(false);

        venueRepository.save(venue);
    }

    @Override
    @Transactional(readOnly = true)
    public Venue getByIdOrThrow(UUID venueId) {

        return venueRepository.findById(venueId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Venue", "venueId", venueId.toString()));
    }
}