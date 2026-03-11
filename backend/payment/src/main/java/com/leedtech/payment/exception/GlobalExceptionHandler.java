package com.leedtech.payment.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for the payment service.
 * Intercepts exceptions and returns standardized error responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Handles StudentNotFoundException.

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleStudentNotFound(StudentNotFoundException ex) {
        logger.warn("Student not found exception: {}", ex.getMessage());
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    // Handles validation exceptions for request bodies.

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        logger.warn("Validation error: {} errors found", ex.getBindingResult().getErrorCount());
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    // Handles all other uncaught exceptions.

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
        logger.error("An unexpected error occurred: ", ex);
        Map<String, String> error = new HashMap<>();
        error.put("error", "An unexpected error occurred: " + ex.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }
}
