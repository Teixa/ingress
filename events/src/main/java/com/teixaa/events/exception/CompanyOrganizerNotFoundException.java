package com.teixaa.events.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CompanyOrganizerNotFoundException extends RuntimeException {
    public CompanyOrganizerNotFoundException(String resourceName, String fieldName, UUID fieldValue) {
        super(String.format("%s not found with the given input data %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
