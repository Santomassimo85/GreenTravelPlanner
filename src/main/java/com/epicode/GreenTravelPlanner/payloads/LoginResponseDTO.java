package com.epicode.GreenTravelPlanner.payloads;

/**
 * Data Transfer Object (DTO) used to return an authentication token to the client.
 * This record "wraps" the generated JWT or access token so it can be
 * easily sent as a JSON object in the response body.
 * * @param accessToken the security token used for subsequent authorized requests
 */
public record LoginResponseDTO(String accessToken) {}