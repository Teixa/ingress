package com.teixaa.events.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateSessionPriceRequestDto {

    @NotNull
    private UUID sectorId;

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal unitPrice;
}