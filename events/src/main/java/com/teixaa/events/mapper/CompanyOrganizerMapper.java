package com.teixaa.events.mapper;

import com.teixaa.events.dto.request.CompanyOrganizerRequestDto;
import com.teixaa.events.dto.response.CompanyOrganizerResponseDto;
import com.teixaa.events.entity.CompanyOrganizer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyOrganizerMapper {

    CompanyOrganizer toEntity(CompanyOrganizerRequestDto request);

    CompanyOrganizerResponseDto toResponse(CompanyOrganizer entity);
}
