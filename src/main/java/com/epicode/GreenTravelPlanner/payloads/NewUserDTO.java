package com.epicode.GreenTravelPlanner.payloads;

import jakarta.validation.constraints.*;

/**
 * Data Transfer Object (DTO) for user registration.
 * This record captures and validates the information required to create a new user account.
 * It uses Jakarta Validation annotations to ensure data integrity before processing.
 * * @param name     The user's first name (minimum 2 characters)
 * @param surname  The user's last name (minimum 2 characters)
 * @param email    The user's unique email address (must be a valid format)
 * @param password The user's account password (minimum 8 characters)
 */
public record NewUserDTO(
        @NotBlank(message = "Name is required")
        @Size(min = 2, message = "Name must be at least 2 characters")
        String name,

        @NotBlank(message = "Surname is required")
        @Size(min = 2, message = "Surname must be at least 2 characters")
        String surname,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters")
        String password
) {}