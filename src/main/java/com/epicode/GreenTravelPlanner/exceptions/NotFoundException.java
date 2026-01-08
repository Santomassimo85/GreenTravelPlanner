package com.epicode.GreenTravelPlanner.exceptions;

/**
 * Custom exception to be thrown when a requested resource is not found in the database.
 * By extending RuntimeException, it allows for "unchecked" exception handling,
 * meaning it does not force mandatory try-catch blocks throughout the code.
 */
public class NotFoundException extends RuntimeException {

    /**
     * Constructs a new NotFoundException with a specific detail message.
     * @param message the error message describing which resource was missing
     */
    public NotFoundException(String message) {
        super(message);
    }
}