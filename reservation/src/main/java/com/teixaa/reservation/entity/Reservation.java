package com.teixaa.reservation.entity;

import com.teixaa.reservation.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Entity
@Table(name = "reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class Reservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "customer_id", nullable = false)
    private UUID customerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ReservationStatus status;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<ReservationItem> items = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (this.status == null) {
            this.status = ReservationStatus.PENDING;
        }
        if (this.totalAmount == null) {
            this.totalAmount = BigDecimal.ZERO;
        }
    }

    public void addItem(ReservationItem item) {
        item.setReservation(this);
        this.items.add(item);
        if (item.getPrice() != null) {
            this.totalAmount = this.totalAmount.add(item.getPrice().multiply(java.math.BigDecimal.valueOf(item.getQuantity())));
        }
    }

    public void removeItem(ReservationItem item) {
        this.items.remove(item);
        item.setReservation(null);
        if (item.getPrice() != null) {
            this.totalAmount = this.totalAmount.subtract(item.getPrice().multiply(java.math.BigDecimal.valueOf(item.getQuantity())));
        }
    }
}

