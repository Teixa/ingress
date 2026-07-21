package com.teixaa.venue.service;

import com.teixaa.venue.dto.request.CreateSectorRequestDto;
import com.teixaa.venue.dto.request.UpdateSectorRequestDto;
import com.teixaa.venue.dto.response.SectorResponseDto;
import com.teixaa.venue.entity.Sector;

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