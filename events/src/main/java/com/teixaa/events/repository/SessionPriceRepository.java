package com.teixaa.events.repository;

import com.teixaa.events.entity.SessionPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.UUID;

@Repository
public interface SessionPriceRepository extends JpaRepository<SessionPrice, Long> {
    List<SessionPrice> findAllBySessionId(UUID sessionId);
}
