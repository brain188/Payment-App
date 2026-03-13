package com.leedtech.payment.controller;

import com.leedtech.payment.dto.PaymentRequest;
import com.leedtech.payment.dto.PaymentResponse;
import com.leedtech.payment.service.PaymentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling student fee payments.
 */
@RestController
@CrossOrigin(origins = "*")
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Processes a one-time fee payment for a student.
     * 
     * @param request Validated PaymentRequest object from the client.
     * @return ResponseEntity containing the payment processing result (PaymentResponse).
     */
    @PostMapping("/one-time-fee-payment")
    public ResponseEntity<PaymentResponse> processPayment(@Valid @RequestBody PaymentRequest request) {
        logger.info("Received payment request for student: {}", request.studentNumber());
        PaymentResponse response = paymentService.processPayment(request);
        logger.info("Successfully processed payment for student: {}. New balance: {}",
                request.studentNumber(), response.newBalance());
        return ResponseEntity.ok(response);
    }
}
