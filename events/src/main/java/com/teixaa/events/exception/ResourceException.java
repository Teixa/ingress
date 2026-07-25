package com.teixaa.events.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ResourceException extends RuntimeException {

    public ResourceException(String text) {
        super(text);
    }

}
