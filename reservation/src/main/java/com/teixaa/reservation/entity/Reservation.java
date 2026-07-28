package com.teixaa.reservation.entity;


import com.teixaa.reservation.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(
        name = "reservations",
        indexes = {
                @Index(name = "idx_reservation_customer", columnList = "customer_id"),
                @Index(name = "idx_reservation_session", columnList = "session_id"),
                @Index(name = "idx_reservation_status", columnList = "status"),
                @Index(name = "idx_reservation_expires", columnList = "expires_at")
        }
)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Reservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Setter
    @Column(nullable = false)
    private UUID customerId;

    @Setter
    @Column(nullable = false)
    private UUID eventId;

    @Setter
    @Column(nullable = false)
    private UUID sessionId;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Setter
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    private LocalDateTime cancelledAt;

    @Builder.Default
    @OneToMany(
            mappedBy = "reservation",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ReservationItem> items = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (status == null) {
            status = ReservationStatus.PENDING;
        }

        if (totalAmount == null) {
            totalAmount = BigDecimal.ZERO;
        }
    }

    public void addItem(ReservationItem item) {
        item.setReservation(this);
        items.add(item);
    }

    public void removeItem(ReservationItem item) {
        items.remove(item);
        item.setReservation(null);
    }

    public void updateTotalAmount(BigDecimal total) {
        this.totalAmount = total;
    }

    public void confirm() {
        this.status = ReservationStatus.CONFIRMED;
        this.confirmedAt = LocalDateTime.now();
    }

    public void cancel() {
        this.status = ReservationStatus.CANCELLED;
        this.cancelledAt = LocalDateTime.now();
    }

    public void startReservation(Duration expirationTime) {
        this.status = ReservationStatus.PENDING;
        this.expiresAt = LocalDateTime.now().plus(expirationTime);
    }

}