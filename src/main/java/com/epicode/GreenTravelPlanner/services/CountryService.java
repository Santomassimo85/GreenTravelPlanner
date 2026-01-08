package com.epicode.GreenTravelPlanner.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import java.util.List;

@Service
public class CountryService {

    // Usiamo RestTemplate (che hai già configurato in AppConfig)
    private final RestTemplate restTemplate = new RestTemplate();

    public String getCapital(String countryName) {
        try {
            // Chiamata all'API esterna pubblica
            String url = "https://restcountries.com/v3.1/name/" + countryName;
            List<Map<String, Object>> response = restTemplate.getForObject(url, List.class);

            if (response != null && !response.isEmpty()) {
                List<String> capitals = (List<String>) response.get(0).get("capital");
                return capitals.get(0);
            }
        } catch (Exception e) {
            return "Non disponibile";
        }
        return "Non trovata";
    }
}