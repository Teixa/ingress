package com.teixaa.reservation.integration.venue.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatValidationItemRequest {

    private UUID sectorId;

    private UUID seatId;

}