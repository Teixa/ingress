package com.teixaa.reservation.controller.impl;

import com.teixaa.reservation.controller.IReservationController;
import com.teixaa.reservation.dto.request.AddItemRequestDto;
import com.teixaa.reservation.dto.request.CheckoutRequestDto;
import com.teixaa.reservation.dto.response.CartResponseDto;
import com.teixaa.reservation.dto.response.ReservationResponseDto;
import com.teixaa.reservation.service.IReservationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class ReservationControllerImpl implements IReservationController {

    private final IReservationService reservationService;

    @Override
    public ResponseEntity<CartResponseDto> createCart(UUID customerId) {
        CartResponseDto dto = reservationService.createCart(customerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Override
    public ResponseEntity<CartResponseDto> addItem(UUID cartId, @Valid AddItemRequestDto request) {
        CartResponseDto dto = reservationService.addItem(cartId, request);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<ReservationResponseDto> checkout(UUID cartId, @Valid CheckoutRequestDto request) {
        ReservationResponseDto dto = reservationService.checkout(cartId, request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
    }

    @Override
    public ResponseEntity<ReservationResponseDto> findById(UUID id) {
        ReservationResponseDto dto = reservationService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<List<ReservationResponseDto>> listByCustomer(UUID customerId) {
        List<ReservationResponseDto> list = reservationService.listByCustomer(customerId);
        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<ReservationResponseDto> cancel(UUID id) {
        ReservationResponseDto dto = reservationService.cancel(id);
        return ResponseEntity.ok(dto);
    }
}

