package com.teixaa.events.service.impl;

import com.teixaa.events.dto.request.UpdateEventRequestDto;
import com.teixaa.events.dto.response.EventResponseDto;
import com.teixaa.events.enums.EventStatus;
import com.teixaa.events.dto.request.CreateEventRequestDto;
import com.teixaa.events.entity.CompanyOrganizer;
import com.teixaa.events.entity.Event;
import com.teixaa.events.exception.CompanyOrganizerNotFoundException;
import com.teixaa.events.mapper.EventMapper;
import com.teixaa.events.repository.EventRepository;
import com.teixaa.events.service.ICompanyOrganizerService;
import com.teixaa.events.service.IEventService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class EventServiceImpl implements IEventService {


    private ICompanyOrganizerService companyOrganizerService;

    private EventRepository eventRepository;

    private EventMapper eventMapper;

    @Override
    public EventResponseDto saveEvent(CreateEventRequestDto createEventRequestDto) {


        CompanyOrganizer organizer = companyOrganizerService.findEntityById(createEventRequestDto.getCompanyOrganizerId());

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

        Event eventSaved = eventRepository.save(event);
        return eventMapper.toResponse(eventSaved);
    }

    @Override
    @Transactional(readOnly = true)
    public Event findEntityById(UUID id) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() ->
                        new CompanyOrganizerNotFoundException("Company organizer", "uuid", id));

        return event;
    }

    //TODO adicionar findbyemail

    @Transactional(readOnly = true)
    @Override
    public EventResponseDto findById(UUID id) {

        return eventMapper.toResponse(findEntityById(id));
    }

    @Override
    public EventResponseDto update(UUID eventId,
                                   UpdateEventRequestDto request) {


        Event event = findEntityById(eventId);

        eventMapper.updateEntity(request, event);

        event.setCompanyOrganizer(
                companyOrganizerService.findEntityById(request.getCompanyOrganizerId()));

        return eventMapper.toResponse(eventRepository.save(event));
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
