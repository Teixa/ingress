package com.teixaa.events.repository;

import com.teixaa.events.entity.Session;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SessionRepository  extends JpaRepository<Session, UUID> {

    Optional<Session> findByEventIdAndId(UUID eventId, UUID id);

    @EntityGraph(attributePaths = {
            "event",
            "prices"
    })
    Optional<Session> findById(UUID id);
}
