package com.teixaa.events.repository;

import com.teixaa.events.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

public interface SessionRepository  extends JpaRepository<Session, UUID> {

    Optional<Session> findByEventIdAndId(@PathVariable UUID eventId, @PathVariable UUID id);
}
