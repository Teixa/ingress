package com.teixaa.venues.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VenueResponseDto {

    private UUID id;

    private String name;

    private String description;

    private String address;

    private String city;

    private String state;

    private String country;

    private String zipCode;

    private Double latitude;

    private Double longitude;

    private Boolean active;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;
}