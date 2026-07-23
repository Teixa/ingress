package com.teixaa.reservation.repository;

import com.teixaa.reservation.entity.Reservation;
import com.teixaa.reservation.enums.ReservationStatus;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

    List<Reservation> findByCustomerId(UUID customerId);

    List<Reservation> findByStatusAndExpiresAtBefore(ReservationStatus status, LocalDateTime time);

    Optional<Reservation> findByIdAndStatus(UUID id, ReservationStatus status);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({
            @QueryHint(name = "jakarta.persistence.lock.timeout", value = "2000") // 2 segundos
    })
    @Query("select r from Reservation r where r.id = :id")
    Optional<Reservation> findByIdForUpdate(UUID id);
}

