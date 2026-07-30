package com.teixaa.payments.kafka;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentApprovedEvent {

    private UUID reservationId;

    private UUID paymentId;

    private LocalDateTime approvedAt;

}