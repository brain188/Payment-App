package com.leedtech.payment.service;

import com.leedtech.payment.domain.StudentAccount;
import com.leedtech.payment.dto.PaymentRequest;
import com.leedtech.payment.dto.PaymentResponse;
import com.leedtech.payment.exception.StudentNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {

    private StudentAccountRepository repository;
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        repository = new StudentAccountRepository();
        paymentService = new PaymentService(repository);
    }

    @Test
    void testIncentiveTier1() {
        // STU001 has balance 1000000.00
        PaymentRequest request = new PaymentRequest("STU001", new BigDecimal("50000.00"), LocalDate.now());
        PaymentResponse response = paymentService.processPayment(request);

        assertEquals(new BigDecimal("0.01"), response.incentiveRate());
        assertEquals(new BigDecimal("500.00"), response.incentiveAmount());
        assertEquals(new BigDecimal("949500.00"), response.newBalance());
    }

    @Test
    void testIncentiveTier2_Boundary_Lower() {
        PaymentRequest request = new PaymentRequest("STU001", new BigDecimal("100000.00"), LocalDate.now());
        PaymentResponse response = paymentService.processPayment(request);

        assertEquals(new BigDecimal("0.03"), response.incentiveRate());
    }

    @Test
    void testStudentNotFound() {
        PaymentRequest request = new PaymentRequest("UNKNOWN", new BigDecimal("1000.00"), LocalDate.now());
        assertThrows(StudentNotFoundException.class, () -> paymentService.processPayment(request));
    }
}
