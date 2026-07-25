package com.teixaa.reservation.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(
        name = "reservation_items",
        indexes = {
                @Index(name = "idx_item_reservation", columnList = "reservation_id"),
                @Index(name = "idx_item_sector", columnList = "sector_id"),
                @Index(name = "idx_item_seat", columnList = "seat_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ReservationItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Reservation owner
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reservation_id", nullable = false)
    @ToString.Exclude
    private Reservation reservation;

    /**
     * Sector from Venue Service.
     * Always required.
     */
    @Column(nullable = false)
    private UUID sectorId;

    /**
     * Seat from Venue Service.
     *
     * Null when the sector is GENERAL_ADMISSION.
     */
    private UUID seatId;

    /**
     * Number of tickets.
     *
     * Reserved seating:
     *      always = 1
     *
     * General admission:
     *      can be > 1
     */
    @Column(nullable = false)
    private Integer quantity = 1;

    /**
     * Ticket price at reservation time.
     *
     * We store it because prices may change later.
     */
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice = BigDecimal.ZERO;

    public BigDecimal getSubtotal() {

        return unitPrice.multiply(
                BigDecimal.valueOf(quantity)
        );

    }
}