package com.epicode.GreenTravelPlanner.payloads;

// Questo oggetto riceve l'email e la password da Postman
public record LoginDTO(String email, String password) {}