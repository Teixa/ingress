package com.teixaa.reservation.mapper;

import com.teixaa.reservation.dto.response.CartResponseDto;
import com.teixaa.reservation.dto.response.ReservationItemDto;
import com.teixaa.reservation.dto.response.ReservationResponseDto;
import com.teixaa.reservation.entity.Reservation;
import com.teixaa.reservation.entity.ReservationItem;
import com.teixaa.reservation.enums.ReservationStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(source = "status", target = "status", qualifiedByName = "statusToString")
    ReservationResponseDto toResponse(Reservation reservation);

    @Mapping(source = "status", target = "status", qualifiedByName = "statusToString")
    CartResponseDto toCartResponse(Reservation reservation);

    ReservationItemDto toItemDto(ReservationItem item);

    @org.mapstruct.Named("statusToString")
    default String statusToString(ReservationStatus status) {
        return status != null ? status.toString() : null;
    }
}


