package com.teixaa.reservation.controller.impl;

import com.teixaa.reservation.controller.IReservationController;
import com.teixaa.reservation.dto.request.CreateReservationRequestDto;
import com.teixaa.reservation.dto.response.ReservationResponseDto;
import com.teixaa.reservation.service.IReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ReservationControllerImpl implements IReservationController {

    private final IReservationService reservationService;

    @Override
    public ReservationResponseDto create(CreateReservationRequestDto request) {
        return reservationService.create(request);
    }

    @Override
    public ReservationResponseDto findById(UUID id) {
        return reservationService.findById(id);
    }

    @Override
    public List<ReservationResponseDto> findByCustomer(UUID customerId) {
        return reservationService.findByCustomer(customerId);
    }

    @Override
    public void cancel(UUID id) {
        reservationService.cancel(id);
    }
}


