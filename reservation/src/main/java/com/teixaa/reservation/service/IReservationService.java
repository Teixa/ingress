package com.teixaa.reservation.service;

import com.teixaa.reservation.dto.request.AddItemRequestDto;
import com.teixaa.reservation.dto.request.CheckoutRequestDto;
import com.teixaa.reservation.dto.response.CartResponseDto;
import com.teixaa.reservation.dto.response.ReservationResponseDto;

import java.util.List;
import java.util.UUID;

public interface IReservationService {

    CartResponseDto createCart(UUID customerId);

    CartResponseDto addItem(UUID cartId, AddItemRequestDto request);

    CartResponseDto removeItem(UUID cartId, UUID itemId);

    ReservationResponseDto checkout(UUID cartId, CheckoutRequestDto request);

    ReservationResponseDto confirm(UUID reservationId);

    ReservationResponseDto cancel(UUID reservationId);

    ReservationResponseDto findById(UUID reservationId);

    List<ReservationResponseDto> listByCustomer(UUID customerId);

    void expirePendingReservations();
}

