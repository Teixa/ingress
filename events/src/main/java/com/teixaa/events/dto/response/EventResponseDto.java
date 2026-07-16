package com.teixaa.events.dto.response;

import com.teixaa.events.constants.EventCategory;
import com.teixaa.events.constants.EventStatus;
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

    private LocalDateTime updatedAt;
}
