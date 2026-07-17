package com.teixaa.events.repository;

import com.teixaa.events.entity.CompanyOrganizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompanyOrganizerRepository extends JpaRepository<CompanyOrganizer, UUID> {

    CompanyOrganizer findByEmail(String email);
}
