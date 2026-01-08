package com.epicode.GreenTravelPlanner.payloads;

/**
 * Data Transfer Object (DTO) used for user authentication.
 * This record captures the login credentials provided by the client,
 * typically sent via tools like Postman or a frontend login form.
 * * @param email    the unique email address of the user
 * @param password the plain-text password to be verified
 */
public record LoginDTO(String email, String password) {}