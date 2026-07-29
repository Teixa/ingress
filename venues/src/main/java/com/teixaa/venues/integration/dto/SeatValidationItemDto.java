package com.teixaa.venues.integration.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatValidationItemDto {

    @NotNull
    private UUID sectorId;

    private UUID seatId;

}