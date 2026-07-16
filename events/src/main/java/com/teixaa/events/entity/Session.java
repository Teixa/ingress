package com.teixaa.events.entity;

import com.teixaa.events.constants.SessionStatus;
import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "sessions")
@SuperBuilder
public class Session extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private LocalDateTime salesStart;

    @Column(nullable = false)
    private LocalDateTime salesEnd;

    private Duration duration;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SessionStatus status;

    @Column(length = 500)
    private String notes;

    private LocalDateTime gatesOpenAt;

}

//sessao
//-----------------------------------
//id
//evento_id
//data_hora
//status

//ex
//Coldplay
//15/11
//        16/11
//        17/11

//1evento x Nsessoes
