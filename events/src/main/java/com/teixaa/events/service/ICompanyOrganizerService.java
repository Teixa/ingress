package com.teixaa.events.service;

import com.teixaa.events.dto.request.CompanyOrganizerRequestDto;
import com.teixaa.events.dto.response.CompanyOrganizerResponseDto;
import com.teixaa.events.entity.CompanyOrganizer;

import java.util.UUID;

public interface ICompanyOrganizerService {

    CompanyOrganizer findEntityById(UUID id);

    CompanyOrganizerResponseDto findById(UUID id);

    CompanyOrganizerResponseDto create(CompanyOrganizerRequestDto companyOrganizerRequestDto);

    CompanyOrganizerResponseDto findByEmail(String email);



}
