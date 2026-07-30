package com.teixaa.reservation.kafka.topics;

public final class KafkaTopics {

    private KafkaTopics() {
    }

    public static final String RESERVATION_CREATED =
            "reservation-created";

    public static final String PAYMENT_APPROVED =
            "payment-approved";

}