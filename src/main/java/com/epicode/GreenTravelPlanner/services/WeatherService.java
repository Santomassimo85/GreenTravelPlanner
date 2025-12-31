package com.epicode.GreenTravelPlanner.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    public String getCityWeather(String cityName) {
        // Chiamata all'API esterna (usiamo un placeholder come da requisiti slide)
        String url = "https://api.wttr.in/" + cityName + "?format=3";
        return restTemplate.getForObject(url, String.class);
    }
}