package com.teixaa.reservation.service;

import com.teixaa.reservation.dto.request.CreateReservationRequestDto;
import com.teixaa.reservation.dto.response.ReservationResponseDto;

import java.util.List;
import java.util.UUID;

public interface IReservationService {

    ReservationResponseDto create(CreateReservationRequestDto request);

    ReservationResponseDto findById(UUID id);

    List<ReservationResponseDto> findByCustomer(UUID customerId);

    void cancel(UUID reservationId);

}