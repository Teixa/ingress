package com.teixaa.reservation.mapper;

import com.teixaa.reservation.dto.request.CreateReservationItemRequestDto;
import com.teixaa.reservation.dto.request.CreateReservationRequestDto;
import com.teixaa.reservation.integration.venue.dto.SeatValidationItemRequest;
import com.teixaa.reservation.integration.venue.dto.ValidateSeatsRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeatValidationMapper {

    ValidateSeatsRequest toRequest(CreateReservationRequestDto dto);

    SeatValidationItemRequest toRequest(
            CreateReservationItemRequestDto dto);

}