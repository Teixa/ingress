package com.teixaa.venues.dto.response;

import com.teixaa.venues.enums.OccupancyType;
import com.teixaa.venues.enums.SectorCategory;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SectorResponseDto {

    private UUID id;

    private UUID venueId;

    private String venueName;

    private String name;

    private Integer capacity;

    private SectorCategory category;

    private OccupancyType occupancyType;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

}