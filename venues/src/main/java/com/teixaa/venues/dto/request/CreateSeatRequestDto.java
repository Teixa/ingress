package com.teixaa.venues.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSeatRequestDto {

    @NotBlank
    @Size(max = 10)
    private String seatRow;

    @NotBlank
    @Size(max = 10)
    private String seatNumber;
}