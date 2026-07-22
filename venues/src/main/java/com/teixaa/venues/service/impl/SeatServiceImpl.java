package com.teixaa.venues.service.impl;

import com.teixaa.venues.dto.request.CreateSeatRequestDto;
import com.teixaa.venues.dto.request.UpdateSeatRequestDto;
import com.teixaa.venues.dto.response.SeatResponseDto;
import com.teixaa.venues.entity.Seat;
import com.teixaa.venues.entity.Sector;
import com.teixaa.venues.enums.OccupancyType;
import com.teixaa.venues.exception.ResourceNotFoundException;
import com.teixaa.venues.exception.SeatException;
import com.teixaa.venues.mapper.SeatMapper;
import com.teixaa.venues.repository.SeatRepository;
import com.teixaa.venues.service.ISeatService;
import com.teixaa.venues.service.ISectorService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class SeatServiceImpl implements ISeatService {

    private final SeatRepository seatRepository;
    private final ISectorService sectorService;
    private final SeatMapper seatMapper;

    @Override
    public SeatResponseDto create(
            UUID sectorId,
            CreateSeatRequestDto request) {

        Sector sector = sectorService.getByIdOrThrow(sectorId);

        if (sector.getOccupancyType() == OccupancyType.GENERAL_ADMISSION) {
            throw new SeatException(
                    "General admission sectors do not support seats.");
        }

        if (seatRepository.existsBySectorIdAndSeatRowAndSeatNumber(
                sectorId,
                request.getSeatRow(),
                request.getSeatNumber())) {

            throw new SeatException(
                    "Seat already exists in this sector.");
        }

        Seat seat = seatMapper.toEntity(request);

        seat.setSector(sector);

        seat.setActive(true);

        return seatMapper.toResponse(
                seatRepository.save(seat));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SeatResponseDto> findBySector(
            UUID sectorId) {

        return seatRepository.findBySectorId(sectorId)
                .stream()
                .map(seatMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SeatResponseDto findById(
            UUID seatId) {

        return seatMapper.toResponse(
                getByIdOrThrow(seatId));
    }

    @Override
    public SeatResponseDto update(
            UUID seatId,
            UpdateSeatRequestDto request) {

        Seat seat = getByIdOrThrow(seatId);

        seatMapper.updateEntity(request, seat);

        return seatMapper.toResponse(
                seatRepository.save(seat));
    }

    @Override
    public void delete(UUID seatId) {

        Seat seat = getByIdOrThrow(seatId);

        seat.setActive(false);

        seatRepository.save(seat);
    }

    @Override
    @Transactional(readOnly = true)
    public Seat getByIdOrThrow(UUID seatId) {

        return seatRepository.findById(seatId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Seat not found with id", "seatId", seatId.toString()));
    }
}