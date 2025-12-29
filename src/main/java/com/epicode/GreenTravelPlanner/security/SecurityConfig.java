package com.epicode.GreenTravelPlanner.security;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Cripta le password in modo sicuro
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Disabilitiamo CSRF per le REST API
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Per ora lasciamo libero per testare la registrazione
                );
        return http.build();
    }
}