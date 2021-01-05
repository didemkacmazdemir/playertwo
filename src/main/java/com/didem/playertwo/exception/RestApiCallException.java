package com.didem.playertwo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class RestApiCallException extends RuntimeException{
    public RestApiCallException(String exception) {
        super(exception);
    }
}
