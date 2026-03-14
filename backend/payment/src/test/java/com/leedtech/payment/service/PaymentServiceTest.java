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
    void testIncentiveTier2_Boundary_Upper() {
        // Just below 500k should be 3%
        PaymentRequest request = new PaymentRequest("STU001", new BigDecimal("499999.99"), LocalDate.now());
        PaymentResponse response = paymentService.processPayment(request);

        assertEquals(new BigDecimal("0.03"), response.incentiveRate());
    }

    @Test
    void testIncentiveTier3_Boundary_Lower() {
        // Exactly 500k should be 5%
        PaymentRequest request = new PaymentRequest("STU001", new BigDecimal("500000.00"), LocalDate.now());
        PaymentResponse response = paymentService.processPayment(request);

        assertEquals(new BigDecimal("0.05"), response.incentiveRate());
    }

    @Test
    void testIncentiveTier3_High() {
        PaymentRequest request = new PaymentRequest("STU001", new BigDecimal("1000000.00"), LocalDate.now());
        PaymentResponse response = paymentService.processPayment(request);

        assertEquals(new BigDecimal("0.05"), response.incentiveRate());
    }

    @Test
    void testNextDueDate_Weekday() {
        // Thursday, March 12, 2026 -> + 90 days -> Wednesday, June 10, 2026
        LocalDate startDate = LocalDate.of(2026, 3, 12);
        PaymentRequest request = new PaymentRequest("STU001", new BigDecimal("1000.00"), startDate);
        PaymentResponse response = paymentService.processPayment(request);

        assertEquals(LocalDate.of(2026, 6, 10), response.nextDueDate());
    }

    @Test
    void testNextDueDate_SaturdayAdjustment() {
        // Saturday, April 4, 2026 -> + 90 days -> Friday, July 3, 2026
        LocalDate startDate = LocalDate.of(2026, 4, 5);
        PaymentRequest request = new PaymentRequest("STU001", new BigDecimal("575000.00"), startDate);
        PaymentResponse response = paymentService.processPayment(request);

        // Sunday April 5 + 90 days = Saturday July 4. Should move to Monday July 6.
        assertEquals(LocalDate.of(2026, 7, 6), response.nextDueDate());
    }

    @Test
    void testNextDueDate_SundayAdjustment() {
        // Monday, April 6, 2026 + 90 days = Sunday, July 5, 2026 -> Should move to
        // Monday July 6.
        LocalDate startDate = LocalDate.of(2026, 4, 6);
        PaymentRequest request = new PaymentRequest("STU001", new BigDecimal("1000.00"), startDate);
        PaymentResponse response = paymentService.processPayment(request);

        assertEquals(LocalDate.of(2026, 7, 6), response.nextDueDate());
    }

    @Test
    void testStudentNotFound() {
        PaymentRequest request = new PaymentRequest("UNKNOWN", new BigDecimal("1000.00"), LocalDate.now());
        assertThrows(StudentNotFoundException.class, () -> paymentService.processPayment(request));
    }
}
