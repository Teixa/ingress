package com.teixaa.events.controller.impl;

import com.teixaa.events.constants.CompanyOrganizerConstants;
import com.teixaa.events.constants.EventConstants;
import com.teixaa.events.controller.IEventController;

import com.teixaa.events.dto.ResponseDto;
import com.teixaa.events.dto.request.CreateEventRequestDto;
import com.teixaa.events.dto.response.EventResponseDto;
import com.teixaa.events.service.IEventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Validated
public class EventControllerImpl implements IEventController {

    IEventService eventService;

    public EventControllerImpl(IEventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public ResponseEntity<ResponseDto> createEvent(@Valid @RequestBody CreateEventRequestDto createEventRequestDto) {
        eventService.saveEvent(createEventRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(EventConstants.STATUS_201, EventConstants.MESSAGE_201));

    }

    @Override
    public ResponseEntity<EventResponseDto> findEventById(@RequestParam UUID id) {
        EventResponseDto eventResponseDto = eventService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(eventResponseDto);
    }

}

//Responsas:
//Criar evento
//Editar evento
//Cancelar evento
//Datas
//Sessões
//Organizadores

//ex coldplay, dias 01/01/2027, 02/01/2027 e 04/01/2027 add hora tbm
// organizador 30e
// fazer crud completo

//disparar kafka
//EventoCriado
//EventoAlterado
//EventoCancelado