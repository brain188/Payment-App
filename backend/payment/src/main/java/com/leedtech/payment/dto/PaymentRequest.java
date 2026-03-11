package com.leedtech.payment.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Data Transfer Object for payment requests.
 */
public record PaymentRequest(
    @NotBlank(message = "Student number is required")
    String studentNumber,

    @NotNull(message = "Payment amount is required")
    @DecimalMin(value = "0.01", message = "Payment amount must be greater than zero")
    BigDecimal paymentAmount,

    LocalDate paymentDate
) {
    public static PaymentRequest.PaymentRequestBuilder builder() {
        return new PaymentRequest.PaymentRequestBuilder();
    }

    public static class PaymentRequestBuilder {
        private String studentNumber;
        private BigDecimal paymentAmount;
        private LocalDate paymentDate;

        public PaymentRequestBuilder studentNumber(String studentNumber) {
            this.studentNumber = studentNumber;
            return this;
        }

        public PaymentRequestBuilder paymentAmount(BigDecimal paymentAmount) {
            this.paymentAmount = paymentAmount;
            return this;
        }

        public PaymentRequestBuilder paymentDate(LocalDate paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }

        public PaymentRequest build() {
            return new PaymentRequest(studentNumber, paymentAmount, paymentDate);
        }
    }
}
