package com.teixaa.events.repository;

import com.teixaa.events.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SessionRepository  extends JpaRepository<Session, UUID> {
}
