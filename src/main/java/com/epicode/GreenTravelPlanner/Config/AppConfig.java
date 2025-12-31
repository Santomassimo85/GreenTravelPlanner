package com.epicode.GreenTravelPlanner.Config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class AppConfig {

    // Questo serve per chiamare le API esterne (Meteo)
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // Questo serve per caricare le immagini (Cloudinary)
    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "tuo_cloud_name");
        config.put("api_key", "tua_api_key");
        config.put("api_secret", "tuo_api_secret");
        return new Cloudinary(config);
    }
}