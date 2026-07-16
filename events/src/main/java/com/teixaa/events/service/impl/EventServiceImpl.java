package com.teixaa.events.service.impl;

import com.teixaa.events.constants.EventStatus;
import com.teixaa.events.dto.request.CreateEventRequestDto;
import com.teixaa.events.entity.CompanyOrganizer;
import com.teixaa.events.entity.Event;
import com.teixaa.events.repository.EventRepository;
import com.teixaa.events.service.ICompanyOrganizerService;
import com.teixaa.events.service.IEventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventServiceImpl implements IEventService {


    private ICompanyOrganizerService companyOrganizerService;

    private EventRepository eventRepository;

    @Override
    public void saveEvent(CreateEventRequestDto createEventRequestDto) {


        CompanyOrganizer organizer = companyOrganizerService.findById(createEventRequestDto.getCompanyOrganizerId());

        Event event = Event.builder().
                name(createEventRequestDto.getName())
                .description(createEventRequestDto.getDescription())
                .imageUrl(createEventRequestDto.getImageUrl())
                .bannerUrl(createEventRequestDto.getBannerUrl())
                .minimumAge(createEventRequestDto.getMinimumAge())
                .eventCategory(createEventRequestDto.getEventCategory())
                .companyOrganizer(organizer)
                .venueId(createEventRequestDto.getVenueId())
                .status(EventStatus.DRAFT)
                .build();

        eventRepository.save(event);
    }
}

//INSERT INTO company_organizers (
//        id,
//        company_name,
//        cnpj,
//        email,
//        phone_number,
//        created_at,
//        created_by,
//        updated_at,
//        updated_by
//        ) VALUES (
//RANDOM_UUID(),
//    'Nome da Empresa Ltda',
//            '12.345.678/0001-90',
//            'contato@empresa.com',
//            '+55 11 99999-9999',
//CURRENT_TIMESTAMP,
//        'sistema_admin',
//CURRENT_TIMESTAMP,
//        'sistema_admin'
//        );
