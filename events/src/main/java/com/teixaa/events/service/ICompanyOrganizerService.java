package com.teixaa.events.service;

import com.teixaa.events.entity.CompanyOrganizer;

import java.util.UUID;

public interface ICompanyOrganizerService {
    public CompanyOrganizer findById(UUID id);
}
