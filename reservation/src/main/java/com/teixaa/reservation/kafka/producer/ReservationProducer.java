package com.teixaa.reservation.kafka.producer;

import com.teixaa.reservation.kafka.events.ReservationCreatedEvent;
import com.teixaa.reservation.kafka.topics.KafkaTopics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publish(ReservationCreatedEvent event) {

        kafkaTemplate.send(
                KafkaTopics.RESERVATION_CREATED,
                event.getReservationId().toString(),
                event
        );

        log.info("Reservation {} published.", event.getReservationId());
    }
}