package com.teixaa.events.controller.impl;

import com.teixaa.events.constants.CompanyOrganizerConstants;
import com.teixaa.events.controller.ICompanyOrganizerController;
import com.teixaa.events.dto.ResponseDto;
import com.teixaa.events.dto.request.CompanyOrganizerRequestDto;
import com.teixaa.events.dto.request.CreateEventRequestDto;
import com.teixaa.events.dto.response.CompanyOrganizerResponseDto;
import com.teixaa.events.service.ICompanyOrganizerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class CompanyOrganizerControllerImpl implements ICompanyOrganizerController {

    ICompanyOrganizerService companyOrganizerService;

    public CompanyOrganizerControllerImpl(ICompanyOrganizerService companyOrganizerService) {
        this.companyOrganizerService = companyOrganizerService;
    }

    @Override
    public ResponseEntity<CompanyOrganizerResponseDto> createCompanyOrganizer(CompanyOrganizerRequestDto companyOrganizerRequestDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(companyOrganizerService.create(companyOrganizerRequestDto));
    }

    @Override
    public ResponseEntity<CompanyOrganizerResponseDto> fetchCompanyOrganizer(String email) {

        return ResponseEntity.status(HttpStatus.OK).body(companyOrganizerService.findByEmail(email));

    }


}
