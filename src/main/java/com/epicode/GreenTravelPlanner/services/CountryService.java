package com.epicode.GreenTravelPlanner.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import java.util.List;

/**
 * Service responsible for interacting with external geographic APIs.
 * It provides methods to fetch country-specific information, such as capital cities,
 * using the RestCountries public API.
 */
@Service
public class CountryService {

    /**
     * Internal instance of RestTemplate to handle HTTP requests.
     */
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Retrieves the capital city of a given country from an external API.
     * * @param countryName the name of the country to search for
     * @return the name of the capital city, "Not available" on error, or "Not found" if no result exists
     */
    public String getCapital(String countryName) {
        try {
            // Call to the public external API
            String url = "https://restcountries.com/v3.1/name/" + countryName;
            List<Map<String, Object>> response = restTemplate.getForObject(url, List.class);

            if (response != null && !response.isEmpty()) {
                // Extract the "capital" field from the first result in the list
                List<String> capitals = (List<String>) response.get(0).get("capital");
                return capitals.get(0);
            }
        } catch (Exception e) {
            // Log or handle the exception appropriately; returning a fallback string for now
            return "Not available";
        }
        return "Not found";
    }
}