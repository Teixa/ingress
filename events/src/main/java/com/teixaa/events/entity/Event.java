package com.teixaa.events.entity;

import com.teixaa.events.enums.EventCategory;
import com.teixaa.events.enums.EventStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "events")
@SuperBuilder
public class Event extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 3000)
    private String description;

    @Column(length = 500)
    private String imageUrl;

    @Column(length = 500)
    private String bannerUrl;

    @Column(nullable = false)
    private Integer minimumAge;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventCategory eventCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_organizer_id", nullable = false)
    private CompanyOrganizer companyOrganizer;

    // Belongs to Local Service
    @Column(nullable = false)
    private UUID venueId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventStatus status;

    @OneToMany(mappedBy = "event",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Session> sessions = new ArrayList<>();
}

//evento
//-------------------------------
//id (UUID)
//nome
//descricao
//categoria
//organizador_id
//local_id
//status
//data_criacao
//data_atualizacao
