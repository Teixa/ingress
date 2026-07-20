package com.teixaa.venue.entity;

import jakarta.persistence.*;
        import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "sectors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Sector extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id", nullable = false)
    private Venue venue;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private Boolean numberedSeats;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SectorType sectorType;

    @OneToMany(
            mappedBy = "sector",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Seat> seats = new ArrayList<>();
}