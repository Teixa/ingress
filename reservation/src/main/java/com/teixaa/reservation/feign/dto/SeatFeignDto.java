package com.teixaa.reservation.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatFeignDto {
    private UUID id;
    private UUID sectorId;
    private String seatRow;
    private String seatNumber;
    private Boolean active;
}

