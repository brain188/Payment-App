package com.leedtech.payment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a student is not found in the repository.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String message) {
        super(message);
    }
}
