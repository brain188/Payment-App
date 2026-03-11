package com.leedtech.payment.service;

import com.leedtech.payment.domain.StudentAccount;
import com.leedtech.payment.dto.PaymentRequest;
import com.leedtech.payment.dto.PaymentResponse;
import com.leedtech.payment.exception.StudentNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Service class for processing student fee payments.
 * Contains business logic for calculating incentive rates and next due dates.
 */
@Service
public class PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);
    private final StudentAccountRepository repository;

    public PaymentService(StudentAccountRepository repository) {
        this.repository = repository;
    }

    /**
     * Processes a payment for a student.
     * Calculates incentives based on the payment amount and determines the next due
     * date.
     */

    public PaymentResponse processPayment(PaymentRequest request) {
        logger.debug("Processing payment for student number: {}", request.studentNumber());

        StudentAccount account = repository.findByStudentNumber(request.studentNumber())
                .orElseThrow(() -> {
                    logger.warn("Student not found with number: {}", request.studentNumber());
                    return new StudentNotFoundException("Student not found: " + request.studentNumber());
                });

        BigDecimal previousBalance = account.getBalance();
        BigDecimal paymentAmount = request.paymentAmount();

        BigDecimal incentiveRate = calculateIncentiveRate(paymentAmount);
        BigDecimal incentiveAmount = paymentAmount.multiply(incentiveRate).setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalDeduction = paymentAmount.add(incentiveAmount);
        BigDecimal newBalance = previousBalance.subtract(totalDeduction).setScale(2, RoundingMode.HALF_UP);

        logger.debug("Payment calculations - Previous: {}, Payment: {}, Rate: {}, Incentive: {}, New: {}",
                previousBalance, paymentAmount, incentiveRate, incentiveAmount, newBalance);

        LocalDate paymentDate = request.paymentDate() != null ? request.paymentDate() : LocalDate.now();
        LocalDate nextDueDate = calculateNextDueDate(paymentDate);

        // Update account balance
        account.setBalance(newBalance);
        repository.save(account);

        logger.info("Payment processed successfully for student: {}. Incentive of {} applied.",
                request.studentNumber(), incentiveAmount);

        return PaymentResponse.builder()
                .studentNumber(account.getStudentNumber())
                .previousBalance(previousBalance)
                .paymentAmount(paymentAmount)
                .incentiveRate(incentiveRate)
                .incentiveAmount(incentiveAmount)
                .newBalance(newBalance)
                .nextDueDate(nextDueDate)
                .build();
    }

    /**
     * Calculates the incentive rate based on the payment amount.
     * Incentives:
     * - Under 100,000: 1%
     * - 100,000 to 499,999: 3%
     * - 500,000 and above: 5%
     */

    private BigDecimal calculateIncentiveRate(BigDecimal amount) {
        if (amount.compareTo(new BigDecimal("100000")) < 0) {
            return new BigDecimal("0.01");
        } else if (amount.compareTo(new BigDecimal("500000")) < 0) {
            return new BigDecimal("0.03");
        } else {
            return new BigDecimal("0.05");
        }
    }

    /**
     * Calculates the next due date, which is 90 days after the current payment
     * date.
     * If the resulting date falls on a weekend, it is moved to the next Monday.
     */

    private LocalDate calculateNextDueDate(LocalDate paymentDate) {
        LocalDate nextDueDate = paymentDate.plusDays(90);
        DayOfWeek dayOfWeek = nextDueDate.getDayOfWeek();

        if (dayOfWeek == DayOfWeek.SATURDAY) {
            return nextDueDate.plusDays(2);
        } else if (dayOfWeek == DayOfWeek.SUNDAY) {
            return nextDueDate.plusDays(1);
        }
        return nextDueDate;
    }
}
