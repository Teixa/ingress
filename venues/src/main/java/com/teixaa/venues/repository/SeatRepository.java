package com.teixaa.venues.repository;

import com.teixaa.venues.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SeatRepository extends JpaRepository<Seat, UUID> {

    List<Seat> findBySectorId(UUID sectorId);

    boolean existsBySectorIdAndSeatRowAndSeatNumber(
            UUID sectorId,
            String seatRow,
            String seatNumber);

}