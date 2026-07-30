package com.teixaa.reservation.mapper;

import com.teixaa.reservation.entity.Reservation;
import com.teixaa.reservation.kafka.events.ReservationCreatedEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationEventMapper {

    @Mapping(target = "reservationId", source = "id")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    ReservationCreatedEvent toCreatedEvent(Reservation reservation);

}