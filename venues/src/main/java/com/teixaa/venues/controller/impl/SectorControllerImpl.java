package com.teixaa.venues.controller.impl;

import com.teixaa.venues.controller.ISectorController;
import com.teixaa.venues.dto.request.CreateSectorRequestDto;
import com.teixaa.venues.dto.request.UpdateSectorRequestDto;
import com.teixaa.venues.dto.response.SectorResponseDto;
import com.teixaa.venues.service.ISectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Validated
public class SectorControllerImpl implements ISectorController {

    private final ISectorService sectorService;

    @Override
    public SectorResponseDto create(
            UUID venueId,
            CreateSectorRequestDto request) {

        return sectorService.create(venueId, request);
    }

    @Override
    public List<SectorResponseDto> findByVenue(UUID venueId) {

        return sectorService.findByVenue(venueId);
    }

    @Override
    public SectorResponseDto findById(UUID sectorId) {

        return sectorService.findById(sectorId);
    }

    @Override
    public SectorResponseDto update(
            UUID sectorId,
            UpdateSectorRequestDto request) {

        return sectorService.update(sectorId, request);
    }

    @Override
    public void delete(UUID sectorId) {

        sectorService.delete(sectorId);
    }
}