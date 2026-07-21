package com.teixaa.venue.dto.request;

import com.teixaa.venue.enums.OccupancyType;
import com.teixaa.venue.enums.SectorCategory;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSectorRequestDto {

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotNull
    @Min(1)
    private Integer capacity;

    @NotNull
    private SectorCategory category;

    @NotNull
    private OccupancyType occupancyType;

}

//{
//        "name": "Cadeira Inferior Oeste",
//        "capacity": 10000,
//        "category": "PREMIUM",
//        "occupancyType": "RESERVED_SEATING"
//        }

//{
//        "name": "Pista premium",
//        "capacity": 10000,
//        "category": "PREMIUM",
//        "occupancyType": "GENERAL_ADMISSION"
//        }

//{
//        "name": "Pista",
//        "capacity": 10000,
//        "category": "GENERAL",
//        "occupancyType": "GENERAL_ADMISSION"
//        }
