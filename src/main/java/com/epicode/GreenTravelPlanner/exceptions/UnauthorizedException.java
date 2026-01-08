package com.epicode.GreenTravelPlanner.exceptions;

/**
 * Custom exception to be thrown when authentication fails.
 * This is typically used during login if credentials are invalid or
 * when a required security token is missing or expired.
 */
public class UnauthorizedException extends RuntimeException {

    /**
     * Constructs a new UnauthorizedException with a detailed error message.
     * @param message the explanation for the authentication failure
     */
    public UnauthorizedException(String message) {
        super(message);
    }
}