package com.teixaa.venues.integration.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidateSeatsRequest {

    @NotEmpty
    private List<SeatValidationItemDto> items;

}