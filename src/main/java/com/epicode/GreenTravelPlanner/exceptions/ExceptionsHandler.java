package com.epicode.GreenTravelPlanner.exceptions;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {

    // 1. Gestisce gli errori di validazione (es. email malformata o campi vuoti)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsPayload handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errorsList = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .toList();
        return new ErrorsPayload("Dati non validi", LocalDateTime.now(), errorsList);
    }

    // 2. Gestisce errori generici (es. email già esistente o risorsa non trovata)
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsPayload handleBadRequest(BadRequestException ex) {
        return new ErrorsPayload(ex.getMessage(), LocalDateTime.now(), null);
    }

    // 3. Gestisce tutti gli altri errori imprevisti (Errore 500)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsPayload handleGenericError(Exception ex) {
        ex.printStackTrace(); // Utile per te in console durante l'esame
        return new ErrorsPayload("Errore interno del server, risolveremo presto!", LocalDateTime.now(), null);
    }
}