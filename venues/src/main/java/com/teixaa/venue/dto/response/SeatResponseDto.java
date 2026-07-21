package com.teixaa.venue.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatResponseDto {

    private UUID id;

    private UUID venueId;
    private String venueName;

    private UUID sectorId;
    private String sectorName;

    private String seatRow;

    private String seatNumber;

    private Boolean active;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}