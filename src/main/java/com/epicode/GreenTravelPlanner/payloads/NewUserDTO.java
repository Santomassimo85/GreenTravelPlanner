package com.epicode.GreenTravelPlanner.payloads;

import jakarta.validation.constraints.*;

public record NewUserDTO(
        @NotBlank(message = "Il nome è obbligatorio")
        @Size(min = 2, message = "Il nome deve avere almeno 2 caratteri")
        String name,

        @NotBlank(message = "Il cognome è obbligatorio")
        @Size(min = 2, message = "Il cognome deve avere almeno 2 caratteri")
        String surname,

        @NotBlank(message = "L'email è obbligatoria")
        @Email(message = "Formato email non valido")
        String email,

        @NotBlank(message = "La password è obbligatoria")
        @Size(min = 8, message = "La password deve essere di almeno 8 caratteri")
        String password
) {}