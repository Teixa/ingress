package com.teixaa.venues.controller.integration.api;

import com.teixaa.venues.integration.dto.ValidateSeatsRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Seat Integration")
@RequestMapping(
        path = "/api/integration/seats",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public interface ISeatIntegrationController {

    @PostMapping("/validate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void validate(@RequestBody @Valid ValidateSeatsRequest request);
}
