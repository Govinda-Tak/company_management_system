package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JWTRequestFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/users/signin", 
                        "/users",
                        "/swagger-ui/**",   // Allow access to Swagger UI
                        "/v3/api-docs/**",  // Allow access to OpenAPI docs
                        "/swagger-resources/**",  // Required for Swagger
                        "/swagger-ui.html",
                        "/webjars/**").permitAll()
                    .requestMatchers("/api/employee/{id}").authenticated()
                    .requestMatchers("/api/employee/{id}", "/api/employee/all-projects/{id}", "/api/employee/all-details/{id}").hasRole("TECHNICAL")
                    .requestMatchers("/api/department/{id}", "/api/department/full-detail/{id}", "/api/department/{id}").hasRole("MANAGER")
                    .requestMatchers("/api/project/{id}", "/api/project/{id}/departments", "/api/project/{id}/employees", "/api/project/all-projects", "/api/project/by-starting-date", "/api/project/complete-details/{id}", "/api/project/all-projects/complete-details").hasRole("MANAGER")
                    .anyRequest().hasRole("ADMIN")
            )
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
