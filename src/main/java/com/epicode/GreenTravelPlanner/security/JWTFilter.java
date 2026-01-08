package com.epicode.GreenTravelPlanner.security;

import com.epicode.GreenTravelPlanner.entities.User;
import com.epicode.GreenTravelPlanner.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

/**
 * Filter responsible for intercepting every incoming HTTP request to validate
 * the JSON Web Token (JWT) provided in the Authorization header.
 */
@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTTools jwtTools;

    private final UserService userService;

    public JWTFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Check if the "Authorization" header is present in the request
        String authHeader = request.getHeader("Authorization");

        // If header is missing or doesn't start with "Bearer ", proceed to the next filter.
        // Protected routes will eventually be blocked by SecurityConfig if authentication is missing.
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Extract the token by removing the "Bearer " prefix
        String token = authHeader.substring(7);

        // 3. Validate the token (checks signature and expiration)
        jwtTools.verifyToken(token);

        // 4. If valid, find the user in the database using the ID embedded in the token
        String id = jwtTools.extractIdFromToken(token);
        User user = userService.findById(Long.parseLong(id));

        // 5. Authenticate the user within the Spring Security context
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 6. Continue the filter chain toward the target Controller
        filterChain.doFilter(request, response);
    }

    /**
     * Defines which requests should bypass this filter.
     * Login and registration endpoints are excluded as they do not require a token.
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().startsWith("/auth/");
    }
}