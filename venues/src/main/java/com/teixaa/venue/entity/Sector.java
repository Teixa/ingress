package com.teixaa.venue.entity;

import com.teixaa.venue.enums.OccupancyType;
import com.teixaa.venue.enums.SectorCategory;
import jakarta.persistence.*;
        import lombok.*;
import lombok.experimental.SuperBuilder;

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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SectorCategory category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OccupancyType occupancyType;

}