package com.teixaa.venue.entity;

import com.teixaa.venue.enums.SectorCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


import java.util.UUID;


@Table(
        name = "seats",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_sector_row_number",
                        columnNames = {
                                "sector_id",
                                "seat_row",
                                "seat_number"
                        })
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Seat extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sector_id", nullable = false)
    private Sector sector;

    @Column(name = "seat_row", nullable = false, length = 10)
    private String row;

    @Column(name = "seat_number", nullable = false, length = 10)
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SectorCategory sectorCategory;

    @Column(nullable = false)
    private Boolean active;
}