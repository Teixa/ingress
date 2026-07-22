package com.teixaa.venues.service;

import com.teixaa.venues.dto.request.CreateSeatRequestDto;
import com.teixaa.venues.dto.request.UpdateSeatRequestDto;
import com.teixaa.venues.dto.response.SeatResponseDto;
import com.teixaa.venues.entity.Seat;

import java.util.List;
import java.util.UUID;

public interface ISeatService {

    SeatResponseDto create(
            UUID sectorId,
            CreateSeatRequestDto request);

    List<SeatResponseDto> findBySector(
            UUID sectorId);

    SeatResponseDto findById(
            UUID seatId);

    SeatResponseDto update(
            UUID seatId,
            UpdateSeatRequestDto request);

    void delete(
            UUID seatId);

    Seat getByIdOrThrow(
            UUID seatId);

}