package com.teixaa.reservation.validator;

import com.teixaa.reservation.dto.request.CreateReservationRequestDto;
import com.teixaa.reservation.dto.request.CreateReservationItemRequestDto;
import com.teixaa.reservation.exception.BusinessException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
public class ReservationValidator {

    public void validate(CreateReservationRequestDto request) {

        Set<UUID> reservedSeats = new HashSet<>();

        for (CreateReservationItemRequestDto item : request.getItems()) {

            if (item.getSeatId() != null) {

                if (!reservedSeats.add(item.getSeatId())) {

                    throw new BusinessException(
                            "Duplicated seat in reservation.");

                }

            }

            if (item.getQuantity() <= 0) {

                throw new BusinessException(
                        "Invalid quantity.");

            }

        }

    }

}