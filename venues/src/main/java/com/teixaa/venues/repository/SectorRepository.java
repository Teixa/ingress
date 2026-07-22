package com.teixaa.venues.repository;

import com.teixaa.venues.entity.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SectorRepository extends JpaRepository<Sector, UUID> {

    List<Sector> findByVenueId(UUID venueId);

    boolean existsByVenueIdAndName(UUID venueId, String name);

}
