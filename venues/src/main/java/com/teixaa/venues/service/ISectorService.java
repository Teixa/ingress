package com.teixaa.venues.service;

import com.teixaa.venues.dto.request.CreateSectorRequestDto;
import com.teixaa.venues.dto.request.UpdateSectorRequestDto;
import com.teixaa.venues.dto.response.SectorResponseDto;
import com.teixaa.venues.entity.Sector;

import java.util.List;
import java.util.UUID;

public interface ISectorService {

    SectorResponseDto create(
            UUID venueId,
            CreateSectorRequestDto request);

    List<SectorResponseDto> findByVenue(
            UUID venueId);

    SectorResponseDto findById(
            UUID sectorId);

    SectorResponseDto update(
            UUID sectorId,
            UpdateSectorRequestDto request);

    void delete(
            UUID sectorId);

    Sector getByIdOrThrow(
            UUID sectorId);
}