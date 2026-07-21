package com.teixaa.venue.service.impl;

import com.teixaa.venue.dto.request.CreateVenueRequestDto;
import com.teixaa.venue.dto.request.UpdateVenueRequestDto;
import com.teixaa.venue.dto.response.VenueResponseDto;
import com.teixaa.venue.entity.Venue;
import com.teixaa.venue.exception.ResourceNotFoundException;
import com.teixaa.venue.mapper.VenueMapper;
import com.teixaa.venue.repository.VenueRepository;
import com.teixaa.venue.service.IVenueService;
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
                                "Venue not found", "venueId", venueId.toString()));
    }
}