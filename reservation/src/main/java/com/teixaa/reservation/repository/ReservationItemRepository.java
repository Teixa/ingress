package com.teixaa.reservation.repository;

import com.teixaa.reservation.entity.ReservationItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservationItemRepository extends JpaRepository<ReservationItem, UUID> {
}

