package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private JWTRequestFilter jwtFilter;

	   @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http.csrf().disable() // Disable CSRF for REST APIs
	            .authorizeHttpRequests(authorizeRequests ->
	                authorizeRequests
	                    .requestMatchers("/users/signin", 
	                                        "/users",
	                                        "/swagger*/**", 
	                                        "/v*/api-docs/**")
	                    .permitAll() // Allow unauthenticated access to these endpoints
	                    .requestMatchers("/api/employee/{id}").hasAnyRole("TECHNICAL", "MANAGER","ADMIN")
	                    .requestMatchers("/api/employee/{id}", "/api/employee/all-projects/{id}", "/api/employee/all-details/{id}").hasRole("TECHNICAL")
	                    .requestMatchers("/api/department/{id}", "/api/department/full-detail/{id}", "/api/department/{id}").hasRole("MANAGER")
	                    .requestMatchers("/api/project/{id}", "/api/project/{id}/departments", "/api/project/{id}/employees", "/api/project/all-projects", "/api/project/by-starting-date", "/api/project/complete-details/{id}", "/api/project/all-projects/complete-details").hasRole("MANAGER")
	                    .anyRequest().hasRole("ADMIN") // All other endpoints require ADMIN role
	            )
	            .sessionManagement(session ->
	                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless sessions
	            )
	            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter

	        return http.build();
	    }

	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();
	    }
}
