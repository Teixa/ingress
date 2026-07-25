package com.teixaa.reservation.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateReservationItemRequestDto {

    @NotNull
    private UUID sectorId;

    /**
     * Obrigatório para setores numerados.
     */
    private UUID seatId;

    /**
     * Para lugares marcados sempre será 1.
     */
    @NotNull
    @Min(1)
    private Integer quantity;
}