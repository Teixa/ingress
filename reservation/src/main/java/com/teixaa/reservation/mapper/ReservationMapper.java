package com.teixaa.reservation.mapper;

import com.teixaa.reservation.dto.response.ReservationItemResponse;
import com.teixaa.reservation.dto.response.ReservationResponse;
import com.teixaa.reservation.entity.Reservation;
import com.teixaa.reservation.entity.ReservationItem;
import com.teixaa.reservation.enums.ReservationStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    ReservationResponse toResponse(Reservation reservation);

    ReservationItemResponse toResponse(ReservationItem item);

    List<ReservationItemResponse> toResponse(List<ReservationItem> items);
}


