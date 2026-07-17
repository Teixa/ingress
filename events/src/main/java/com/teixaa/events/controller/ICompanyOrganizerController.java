package com.teixaa.events.controller;


import com.teixaa.events.dto.ResponseDto;
import com.teixaa.events.dto.request.CompanyOrganizerRequestDto;
import com.teixaa.events.dto.response.ErrorResponseDto;
import com.teixaa.events.dto.response.EventResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "CRUD REST APIs for Company Organizers in Ingress",
        description = "CRUD REST APIs in Ingress to CREATE, UPDATE, FETCH AND DELETE Company Organizer details"
)
@RestController()
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public interface ICompanyOrganizerController {

    @Operation(
            summary = "Create Company Organizer REST API",
            description = "REST API to create an Company Organizer"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = EventResponseDto.class))}
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping(path = "createEvent")
    public ResponseEntity<ResponseDto> createEvent (@Valid @RequestBody CompanyOrganizerRequestDto companyOrganizerRequestDto);

}
