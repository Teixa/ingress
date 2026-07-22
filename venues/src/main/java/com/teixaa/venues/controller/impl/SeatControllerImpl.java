package com.teixaa.venues.controller.impl;

import com.teixaa.venues.controller.ISeatController;
import com.teixaa.venues.dto.request.CreateSeatRequestDto;
import com.teixaa.venues.dto.request.UpdateSeatRequestDto;
import com.teixaa.venues.dto.response.SeatResponseDto;
import com.teixaa.venues.service.ISeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Validated
public class SeatControllerImpl implements ISeatController {

    private final ISeatService seatService;

    @Override
    public SeatResponseDto create(
            UUID sectorId,
            CreateSeatRequestDto request) {

        return seatService.create(sectorId, request);
    }

    @Override
    public List<SeatResponseDto> findBySector(
            UUID sectorId) {

        return seatService.findBySector(sectorId);
    }

    @Override
    public SeatResponseDto findById(UUID seatId) {

        return seatService.findById(seatId);
    }

    @Override
    public SeatResponseDto update(
            UUID seatId,
            UpdateSeatRequestDto request) {

        return seatService.update(seatId, request);
    }

    @Override
    public void delete(UUID seatId) {

        seatService.delete(seatId);
    }
}