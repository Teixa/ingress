package com.teixaa.events.controller.api;

import com.teixaa.events.integration.dto.SessionIntegrationResponse;
import com.teixaa.events.integration.dto.SessionPriceIntegrationResponse;
import com.teixaa.events.integration.dto.SessionSectorPriceIntegrationResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Session Integration")
@RequestMapping(
        path = "/api/integration/sessions",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public interface ISessionIntegrationController {

    @GetMapping("/{sessionId}")
    SessionIntegrationResponse getSession(
            @PathVariable UUID sessionId);

    @GetMapping("/{sessionId}/prices")
    SessionPriceIntegrationResponse getPrices(
            @PathVariable UUID sessionId);

    @GetMapping("/{sessionId}/prices/{sectorId}")
    @ResponseStatus(HttpStatus.OK)
    SessionSectorPriceIntegrationResponse getSectorPrice(@PathVariable UUID sessionId, @PathVariable UUID sectorId);

}