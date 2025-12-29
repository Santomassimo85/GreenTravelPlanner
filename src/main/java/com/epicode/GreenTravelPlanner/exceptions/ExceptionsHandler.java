package com.epicode.GreenTravelPlanner.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Date;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorPayload handleBadRequest(RuntimeException e) {
        return new ErrorPayload(e.getMessage(), new Date());
    }
}

// Record semplice per la risposta d'errore
record ErrorPayload(String message, Date timestamp) {}