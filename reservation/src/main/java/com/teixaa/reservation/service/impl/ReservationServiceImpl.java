package com.teixaa.reservation.service.impl;

import com.teixaa.reservation.dto.request.CreateReservationRequestDto;
import com.teixaa.reservation.dto.response.ReservationResponseDto;
import com.teixaa.reservation.entity.Reservation;
import com.teixaa.reservation.enums.ReservationStatus;
import com.teixaa.reservation.exception.BusinessException;
import com.teixaa.reservation.exception.ResourceNotFoundException;
import com.teixaa.reservation.mapper.ReservationMapper;
import com.teixaa.reservation.repository.ReservationRepository;
import com.teixaa.reservation.service.IPricingService;
import com.teixaa.reservation.service.IReservationService;
import com.teixaa.reservation.validator.ReservationValidator;
import com.teixaa.reservation.validator.SeatValidator;
import com.teixaa.reservation.validator.SessionValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ReservationServiceImpl implements IReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final ReservationValidator reservationValidator;
    private final SessionValidator sessionValidator;
    private final IPricingService pricingService;
    private final SeatValidator seatValidator;

    @Override
    public ReservationResponseDto create(CreateReservationRequestDto request) {

        reservationValidator.validate(request);

        sessionValidator.validate(request.getSessionId());

        seatValidator.validate(request);

        Reservation reservation =
                reservationMapper.toEntity(request);

        reservation.startReservation(Duration.ofMinutes(15));

        pricingService.calculate(reservation);

        reservationRepository.save(reservation);

        return reservationMapper.toResponse(reservation);
    }

    @Override
    @Transactional(readOnly = true)
    public ReservationResponseDto findById(UUID id) {

        Reservation reservation =
                reservationRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Reservation",
                                        "id",
                                        id.toString()));

        return reservationMapper.toResponse(reservation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservationResponseDto> findByCustomer(UUID customerId) {

        return reservationRepository.findByCustomerId(customerId)
                .stream()
                .map(reservationMapper::toResponse)
                .toList();
    }

    @Override
    public void cancel(UUID reservationId) {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Reservation",
                        "id",
                        reservationId.toString()));

        if (reservation.getStatus() == ReservationStatus.CONFIRMED) {
            throw new BusinessException(
                    "Confirmed reservations cannot be cancelled.");
        }

        if (reservation.getStatus() == ReservationStatus.CANCELLED) {
            throw new BusinessException(
                    "Reservation is already cancelled.");
        }

        reservation.cancel();

        reservationRepository.save(reservation);
    }

}






