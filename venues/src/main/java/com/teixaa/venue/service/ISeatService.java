package com.teixaa.venue.service;

import com.teixaa.venue.dto.request.CreateSeatRequestDto;
import com.teixaa.venue.dto.request.UpdateSeatRequestDto;
import com.teixaa.venue.dto.response.SeatResponseDto;
import com.teixaa.venue.entity.Seat;

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