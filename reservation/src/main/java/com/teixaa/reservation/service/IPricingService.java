package com.teixaa.reservation.service;

import com.teixaa.reservation.entity.Reservation;
import com.teixaa.reservation.entity.ReservationItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface IPricingService {

    void calculate(Reservation reservation);
}