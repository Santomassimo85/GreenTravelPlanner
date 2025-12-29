package com.epicode.GreenTravelPlanner.payloads;

import jakarta.validation.constraints.*;

public record NewUserDTO(
        @NotBlank(message = "Il nome è obbligatorio") String name,
        @NotBlank(message = "Il cognome è obbligatorio") String surname,
        @Email(message = "L'email non è valida") String email,
        @NotBlank(message = "La password è obbligatoria") @Size(min = 8) String password
) {}