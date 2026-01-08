package com.epicode.GreenTravelPlanner.payloads;

/**
 * Data Transfer Object (DTO) used for user login requests.
 * This record simplifies the transfer of authentication credentials
 * from the client-side to the security service.
 * * @param email    the email address used as the username
 * @param password the raw password string to be authenticated
 */
public record UserLoginDTO(String email, String password) {}