package com.teixaa.events.dto.request;

import com.teixaa.events.enums.EventCategory;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateEventRequestDto {

    @NotBlank
    @Size(max = 150)
    private String name;

    @NotBlank
    @Size(max = 3000)
    private String description;

    @Size(max = 500)
    private String imageUrl;

    @Size(max = 500)
    private String bannerUrl;

    @NotNull
    @Min(0)
    private Integer minimumAge;

    @NotNull
    private EventCategory eventCategory;

    @NotNull
    private UUID companyOrganizerId;

    @NotNull
    private UUID venueId;
}