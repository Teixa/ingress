package com.teixaa.events.controller.impl;

import com.teixaa.events.constants.CompanyOrganizerConstants;
import com.teixaa.events.controller.ICompanyOrganizerController;
import com.teixaa.events.dto.ResponseDto;
import com.teixaa.events.dto.request.CompanyOrganizerRequestDto;
import com.teixaa.events.dto.request.CreateEventRequestDto;
import com.teixaa.events.service.ICompanyOrganizerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class CompanyOrganizerControllerImpl implements ICompanyOrganizerController {

    ICompanyOrganizerService companyOrganizerService;

    @Override
    public ResponseEntity<ResponseDto> createEvent(CompanyOrganizerRequestDto companyOrganizerRequestDto) {
        companyOrganizerService.create(companyOrganizerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(CompanyOrganizerConstants.STATUS_201, CompanyOrganizerConstants.MESSAGE_201));
    }
}
