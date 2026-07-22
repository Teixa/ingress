package com.teixaa.venues.service.impl;

import com.teixaa.venues.dto.request.CreateSectorRequestDto;
import com.teixaa.venues.dto.request.UpdateSectorRequestDto;
import com.teixaa.venues.dto.response.SectorResponseDto;
import com.teixaa.venues.entity.Sector;
import com.teixaa.venues.entity.Venue;
import com.teixaa.venues.exception.ResourceNotFoundException;
import com.teixaa.venues.exception.SectorAlreadyExists;
import com.teixaa.venues.mapper.SectorMapper;
import com.teixaa.venues.repository.SectorRepository;
import com.teixaa.venues.service.ISectorService;
import com.teixaa.venues.service.IVenueService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class SectorServiceImpl implements ISectorService {

    private final SectorRepository sectorRepository;
    private final IVenueService venueService;
    private final SectorMapper sectorMapper;

    @Override
    public SectorResponseDto create(
            UUID venueId,
            CreateSectorRequestDto request) {

        Venue venue = venueService.getByIdOrThrow(venueId);

        Sector sector = sectorMapper.toEntity(request);

        sector.setVenue(venue);

        if (sectorRepository.existsByVenueIdAndName(venueId, request.getName())) {
            throw new SectorAlreadyExists("Sector name already exists for this venue.","sector.getName()",sector.getName());
        }

        return sectorMapper.toResponse(
                sectorRepository.save(sector));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SectorResponseDto> findByVenue(
            UUID venueId) {

        return sectorRepository.findByVenueId(venueId)
                .stream()
                .map(sectorMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SectorResponseDto findById(
            UUID sectorId) {

        return sectorMapper.toResponse(
                getByIdOrThrow(sectorId));
    }

    @Override
    public SectorResponseDto update(
            UUID sectorId,
            UpdateSectorRequestDto request) {

        Sector sector = getByIdOrThrow(sectorId);

        sectorMapper.updateEntity(request, sector);

        return sectorMapper.toResponse(
                sectorRepository.save(sector));
    }

    @Override
    public void delete(UUID sectorId) {

        Sector sector = getByIdOrThrow(sectorId);

        sectorRepository.delete(sector);
    }

    @Override
    @Transactional(readOnly = true)
    public Sector getByIdOrThrow(UUID sectorId) {

        return sectorRepository.findById(sectorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Sector not found", "sectorId", sectorId.toString()));
    }
}