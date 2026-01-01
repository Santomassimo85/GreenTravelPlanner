package com.epicode.GreenTravelPlanner.exceptions;

// Estendiamo RuntimeException così non dobbiamo gestire i blocchi try-catch ovunque
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}