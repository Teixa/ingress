package com.teixaa.events.service.impl;

import com.teixaa.events.dto.request.CompanyOrganizerRequestDto;
import com.teixaa.events.dto.response.CompanyOrganizerResponseDto;
import com.teixaa.events.entity.CompanyOrganizer;
import com.teixaa.events.exception.CompanyOrganizerNotFoundException;
import com.teixaa.events.mapper.CompanyOrganizerMapper;
import com.teixaa.events.repository.CompanyOrganizerRepository;
import com.teixaa.events.service.ICompanyOrganizerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyOrganizerServiceImpl implements ICompanyOrganizerService {

    private final CompanyOrganizerRepository companyOrganizerRepository;
    private final CompanyOrganizerMapper companyOrganizerMapper;

    @Override
    public CompanyOrganizer findById(UUID id) {
        return companyOrganizerRepository.findById(id)
                .orElseThrow(() ->
                        new CompanyOrganizerNotFoundException("Company organizer", "uuid", id));
    }

    @Override
    public CompanyOrganizerResponseDto create(CompanyOrganizerRequestDto companyOrganizerRequestDto) {

        CompanyOrganizer companyOrganizer = companyOrganizerMapper.toEntity(companyOrganizerRequestDto);

        CompanyOrganizer companyOrganizerSaved = companyOrganizerRepository.save(companyOrganizer);

        return companyOrganizerMapper.toResponse(companyOrganizerSaved);
    }

    @Override
    public CompanyOrganizerResponseDto findByEmail(String email) {

        CompanyOrganizer companyOrganizer = companyOrganizerRepository.findByEmail(email);

        return companyOrganizerMapper.toResponse(companyOrganizer);

    }
}
