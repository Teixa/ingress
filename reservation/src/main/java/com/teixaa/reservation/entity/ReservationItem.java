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
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ReservationItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @Setter
    @Column(nullable = false)
    private UUID sectorId;

    @Setter
    private UUID seatId;

    @Setter
    @Column(nullable = false)
    private Integer quantity = 1;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice = BigDecimal.ZERO;

    public void updateUnitPrice(BigDecimal price) {
        this.unitPrice = price;
    }

    @Transient
    public BigDecimal getSubtotal() {
        return unitPrice.multiply(
                BigDecimal.valueOf(quantity));
    }

}