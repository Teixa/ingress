package com.teixaa.venues.controller.integration;

import com.teixaa.venues.controller.integration.api.ISeatIntegrationController;
import com.teixaa.venues.integration.dto.ValidateSeatsRequest;
import com.teixaa.venues.service.ISeatIntegrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SeatIntegrationControllerImpl implements ISeatIntegrationController {
    private final ISeatIntegrationService service;

    @Override
    public void validate(
            @Valid @RequestBody ValidateSeatsRequest request) {

        service.validateSeats(request);
    }
}
