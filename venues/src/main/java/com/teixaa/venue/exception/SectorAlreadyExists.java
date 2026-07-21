package com.teixaa.venue.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SectorAlreadyExists extends RuntimeException {

    public SectorAlreadyExists(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s already exists for the given input data %s : '%s'", resourceName, fieldName, fieldValue));
    }

}
