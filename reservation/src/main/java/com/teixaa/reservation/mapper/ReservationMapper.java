package com.teixaa.reservation.mapper;

import com.teixaa.reservation.dto.request.CreateReservationItemRequestDto;
import com.teixaa.reservation.dto.request.CreateReservationRequestDto;
import com.teixaa.reservation.dto.response.ReservationItemResponseDto;
import com.teixaa.reservation.dto.response.ReservationResponseDto;
import com.teixaa.reservation.entity.Reservation;
import com.teixaa.reservation.entity.ReservationItem;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    Reservation toEntity(CreateReservationRequestDto dto);

    ReservationItem toEntity(CreateReservationItemRequestDto dto);

    ReservationResponseDto toResponse(Reservation reservation);

    @Mapping(
            target = "subtotal",
            expression = "java(item.getSubtotal())"
    )
    ReservationItemResponseDto toResponse(ReservationItem item);

    @AfterMapping
    default void linkItems(@MappingTarget Reservation reservation) {

        if (reservation.getItems() == null) {
            return;
        }

        reservation.getItems().forEach(item ->
                item.setReservation(reservation));
    }

}