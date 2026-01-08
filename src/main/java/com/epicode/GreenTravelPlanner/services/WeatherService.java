package com.epicode.GreenTravelPlanner.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service responsible for fetching real-time weather information.
 * It integrates with external weather providers to give users updates
 * on their travel destinations.
 */
@Service
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Retrieves a brief weather summary for a specific city.
     * Uses the wttr.in service to get a formatted string (e.g., "London: +15°C").
     * * @param cityName the name of the city to query
     * @return a string containing the weather report, or a fallback message if unavailable
     */
    public String getCityWeather(String cityName) {
        try {
            // External API call using the placeholder service as per project requirements
            String url = "https://api.wttr.in/" + cityName + "?format=3";
            String response = restTemplate.getForObject(url, String.class);

            return (response != null) ? response : "Weather data currently unavailable";
        } catch (Exception e) {
            // Fallback in case of network issues or API downtime
            return "Unable to fetch weather for " + cityName;
        }
    }
}