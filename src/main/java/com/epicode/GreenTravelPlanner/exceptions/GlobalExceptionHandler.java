package com.epicode.GreenTravelPlanner.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.security.access.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Global exception handler for the application.
 * This class intercepts exceptions thrown by controllers and returns
 * standardized JSON error responses to the client.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles cases where a requested resource is not found (404 Not Found).
     * @param ex the NotFoundException thrown by the service layer
     * @return a response entity containing error details and 404 status
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("status", HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles validation errors (400 Bad Request).
     * Triggered when @Valid constraints on request bodies are violated.
     * @param ex the validation exception containing binding results
     * @return a response entity with a list of specific field errors
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Validation error in the submitted data");
        body.put("errorsList", errors);
        body.put("status", HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles generic unexpected errors (500 Internal Server Error).
     * @param ex the root exception
     * @return a response entity with a generic error message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericError(Exception ex) {
        ex.printStackTrace(); // Useful for server-side debugging
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Internal server error: " + ex.getMessage());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles authorization errors (403 Forbidden).
     * Triggered when a user tries to access a resource without sufficient permissions.
     * @param ex the AccessDeniedException
     * @return a response entity with a forbidden status
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Access denied: you do not have permission for this operation!");
        body.put("status", HttpStatus.FORBIDDEN.value());

        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }
}