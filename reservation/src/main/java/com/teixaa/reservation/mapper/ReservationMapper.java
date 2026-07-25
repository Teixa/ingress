package com.teixaa.reservation.mapper;

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

    ReservationResponseDto toResponse(Reservation reservation);

    @Mapping(target = "subtotal", expression = "java(item.getSubtotal())")
    ReservationItemResponseDto toResponse(
            ReservationItem item);

    List<ReservationItemResponseDto> toResponse(
            List<ReservationItem> items);



}

