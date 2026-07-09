package com.routesphere.gateway.config;

import com.routesphere.gateway.security.JwtFilter;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers(
                                        "/api/auth/**",
                                        "/api/customer/**",
                                        "/api/driver/**",
                                        "/api/log/**",
                                        "/api/maintenance/**",
                                        "/api/trip/**",
                                        "/api/shipment/**",
                                        "/api/vehicle/**",
                                        "/api/trip/**",
                                        "/api/invoice/**",
                                        "/swagger-ui/**",
                                        "/swagger-ui/swagger-config",
                                        "/swagger-ui.html", 
                                        "/v3/api-docs/**",
                                        "/auth/v3/api-docs",
                                        "/vehicle/v3/api-docs",
                                        "/driver/v3/api-docs",
                                        "/customer/v3/api-docs",
                                        "/shipment/v3/api-docs",
                                        "/invoice/v3/api-docs",
                                        "/trip/v3/api-docs",
                                        "/maintenance/v3/api-docs",
                                        "/log/v3/api-docs",
                                        "/actuator/**"
                        ).permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}