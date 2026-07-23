package com.teixaa.reservation.service;

import com.teixaa.reservation.entity.ReservationItem;

import java.util.UUID;

public interface IReservationItemService {

    ReservationItem save(ReservationItem item);

    ReservationItem findById(UUID itemId);

    void delete(ReservationItem item);

    void deleteById(UUID itemId);
}

