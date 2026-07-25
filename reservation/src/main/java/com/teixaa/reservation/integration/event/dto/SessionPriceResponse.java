package com.teixaa.reservation.integration.event.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessionPriceResponse {

    private UUID sessionId;

    private List<SectorPriceResponse> prices;
}