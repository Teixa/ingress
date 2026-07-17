package com.teixaa.events.dto.response;

import com.teixaa.events.enums.EventCategory;
import com.teixaa.events.enums.EventStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class EventResponseDto {
    private UUID id;

    private String name;

    private String description;

    private String imageUrl;

    private String bannerUrl;

    private Integer minimumAge;

    private EventCategory eventCategory;

    private EventStatus status;

    private UUID venueId;

    private UUID companyOrganizerId;

    private String companyOrganizerName;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;
}
