package com.leedtech.payment.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Data Transfer Object for payment responses.
 */
public record PaymentResponse(
        String studentNumber,
        BigDecimal previousBalance,
        BigDecimal paymentAmount,
        BigDecimal incentiveRate,
        BigDecimal incentiveAmount,
        BigDecimal newBalance,
        LocalDate nextDueDate) {

    public static PaymentResponse.PaymentResponseBuilder builder() {
        return new PaymentResponse.PaymentResponseBuilder();
    }

    public static class PaymentResponseBuilder {
        private String studentNumber;
        private BigDecimal previousBalance;
        private BigDecimal paymentAmount;
        private BigDecimal incentiveRate;
        private BigDecimal incentiveAmount;
        private BigDecimal newBalance;
        private LocalDate nextDueDate;

        public PaymentResponseBuilder studentNumber(String studentNumber) {
            this.studentNumber = studentNumber;
            return this;
        }

        public PaymentResponseBuilder previousBalance(BigDecimal previousBalance) {
            this.previousBalance = previousBalance;
            return this;
        }

        public PaymentResponseBuilder paymentAmount(BigDecimal paymentAmount) {
            this.paymentAmount = paymentAmount;
            return this;
        }

        public PaymentResponseBuilder incentiveRate(BigDecimal incentiveRate) {
            this.incentiveRate = incentiveRate;
            return this;
        }

        public PaymentResponseBuilder incentiveAmount(BigDecimal incentiveAmount) {
            this.incentiveAmount = incentiveAmount;
            return this;
        }

        public PaymentResponseBuilder newBalance(BigDecimal newBalance) {
            this.newBalance = newBalance;
            return this;
        }

        public PaymentResponseBuilder nextDueDate(LocalDate nextDueDate) {
            this.nextDueDate = nextDueDate;
            return this;
        }

        public PaymentResponse build() {
            return new PaymentResponse(studentNumber, previousBalance, paymentAmount, incentiveRate, incentiveAmount,
                    newBalance, nextDueDate);
        }
    }
}
