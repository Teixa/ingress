package com.teixaa.reservation.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateReservationRequest {

    @NotNull
    private UUID customerId;

    @NotNull
    private UUID eventId;

    @NotNull
    private UUID sessionId;

    @NotEmpty
    private List<CreateReservationItemRequest> items;
}