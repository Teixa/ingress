package com.teixaa.reservation.integration.venue.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidateSeatsRequest {

    private List<SeatValidationItemRequest> items;

}