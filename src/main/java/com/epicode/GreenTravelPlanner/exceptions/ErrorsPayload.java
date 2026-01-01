package com.epicode.GreenTravelPlanner.exceptions;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorsPayload(
        String message,
        LocalDateTime timestamp,
        List<String> errorsList // Questo conterrà i dettagli (es: "Email non valida")
) {}