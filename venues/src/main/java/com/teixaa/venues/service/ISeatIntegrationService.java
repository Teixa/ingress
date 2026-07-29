package com.teixaa.venues.service;

import com.teixaa.venues.integration.dto.ValidateSeatsRequest;


public interface ISeatIntegrationService {

    void validateSeats(ValidateSeatsRequest request);

}