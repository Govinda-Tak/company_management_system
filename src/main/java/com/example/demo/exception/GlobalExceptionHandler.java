package com.example.demo.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiException resourceNotFoundExceptionHandler(ResourceNotFoundException exception) {
        return new ApiException(404, exception.getMessage(), ZonedDateTime.now());
    }

    @ExceptionHandler(InvalidInputException.class)
    public ApiException invalidInputExceptionHandler(InvalidInputException exception) {
        return new ApiException(400, exception.getMessage(), ZonedDateTime.now());
    }

    @ExceptionHandler(EmployeeManagementException.class)
    public ApiException EmployeeManagementExceptionHandler(EmployeeManagementException exception) {
        return new ApiException(400, exception.getMessage(), ZonedDateTime.now());
    }

    // Handle 401 Unauthorized
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
        return new ResponseEntity<>(new ApiException(401, "Authentication failed: " + ex.getMessage(), ZonedDateTime.now()), HttpStatus.UNAUTHORIZED);
    }

    // Handle 403 Forbidden
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        return new ResponseEntity<>(new ApiException(403, "Access denied: " + ex.getMessage(), ZonedDateTime.now()), HttpStatus.FORBIDDEN);
    }

    // Handle Bad Credentials
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        return new ResponseEntity<>(new ApiException(401, "Bad credentials: " + ex.getMessage(), ZonedDateTime.now()), HttpStatus.UNAUTHORIZED);
    }

    // Handle other exceptions
    @ExceptionHandler(Exception.class)
    public ApiException handleException(Exception ex, WebRequest request) {
        return new ApiException(500, "An error occurred: " + ex.getMessage(), ZonedDateTime.now());
    }
}
