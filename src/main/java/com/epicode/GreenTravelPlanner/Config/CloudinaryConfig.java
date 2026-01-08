package com.epicode.GreenTravelPlanner.Config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration class for Cloudinary integration.
 * This class defines the bean necessary to interact with the Cloudinary API
 * for managing image and video uploads.
 */
@Configuration
public class CloudinaryConfig {

    /**
     * Creates and configures the Cloudinary bean using credentials provided in
     * the application properties.
     * * @param cloudName the name of the Cloudinary cloud account
     * @param apiKey    the API key for authentication
     * @param apiSecret the API secret for authentication
     * @return a configured Cloudinary instance
     */
    @Bean
    public Cloudinary uploader(@Value("${cloudinary.name}") String cloudName,
                               @Value("${cloudinary.apikey}") String apiKey,
                               @Value("${cloudinary.secret}") String apiSecret) {
        // Map to hold configuration parameters
        Map<String, String> config = new HashMap<>();

        // Mapping property values to Cloudinary required keys
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);

        // Returning the initialized Cloudinary object
        return new Cloudinary(config);
    }
}