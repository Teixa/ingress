package com.teixaa.events.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionPriceResponseDto {

    private UUID sectorId;

    private BigDecimal unitPrice;

}