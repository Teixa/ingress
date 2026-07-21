package com.teixaa.venue.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateVenueRequestDto {

    @NotBlank
    @Size(max = 150)
    private String name;

    @Size(max = 2000)
    private String description;

    @NotBlank
    @Size(max = 250)
    private String address;

    @NotBlank
    @Size(max = 100)
    private String city;

    @NotBlank
    @Size(max = 100)
    private String state;

    @NotBlank
    @Size(max = 100)
    private String country;

    @NotBlank
    @Size(max = 20)
    private String zipCode;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;
}