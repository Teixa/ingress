package com.teixaa.events.service.impl;

import com.teixaa.events.dto.ResponseDto;
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

    private final CompanyOrganizerRepository repository;
    private final CompanyOrganizerMapper mapper;

    @Override
    public CompanyOrganizer findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new CompanyOrganizerNotFoundException("Company organizer", "uuid", id));
    }

    @Override
    public void create(CompanyOrganizerRequestDto companyOrganizerRequestDto) {

        CompanyOrganizer companyOrganizer = mapper.toEntity(companyOrganizerRequestDto);

        repository.save(companyOrganizer);
    }
}
