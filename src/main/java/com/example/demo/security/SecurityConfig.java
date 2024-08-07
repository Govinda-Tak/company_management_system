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
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.service.GitHubUserService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JWTRequestFilter jwtFilter;

    @Autowired
    private GitHubUserService gitHubUserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/users/signin", 
                        "/users",
                        "/swagger-ui/**",   // Allow access to Swagger UI
                        "/v3/api-docs/**",  // Allow access to OpenAPI docs
                        "/swagger-resources/**",  // Required for Swagger
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/oauth2/**").permitAll() // Allow access to OAuth2 login
                    .requestMatchers("/api/employee/{id}").authenticated()
                    .requestMatchers("/api/employee/{id}", "/api/employee/all-projects/{id}", "/api/employee/all-details/{id}").hasRole("TECHNICAL")
                    .requestMatchers("/api/department/{id}", "/api/department/full-detail/{id}", "/api/department/{id}").hasRole("MANAGER")
                    .requestMatchers("/api/project/{id}", "/api/project/{id}/departments", "/api/project/{id}/employees", "/api/project/all-projects", "/api/project/by-starting-date", "/api/project/complete-details/{id}", "/api/project/all-projects/complete-details").hasRole("MANAGER")
                    .anyRequest().hasRole("ADMIN")
            )
            .oauth2Login(oauth2 -> oauth2
                .successHandler(successHandler())
            ) // Enable OAuth2 login and configure success handler
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
            gitHubUserService.processOAuthPostLogin(token);  // Handle user details after successful OAuth2 login

            response.sendRedirect("/");  // Redirect to home or any other page after login
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
