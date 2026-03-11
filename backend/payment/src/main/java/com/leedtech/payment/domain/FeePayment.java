package com.leedtech.payment.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Domain model representing a single fee payment record.
 */
public class FeePayment {
    private BigDecimal paymentAmount;
    private LocalDate paymentDate;
    private BigDecimal incentiveRate;
    private BigDecimal incentiveAmount;
    private BigDecimal newBalance;
    private LocalDate nextDueDate;

    public FeePayment() {
    }

    public FeePayment(BigDecimal paymentAmount,
            LocalDate paymentDate,
            BigDecimal incentiveRate,
            BigDecimal incentiveAmount,
            BigDecimal newBalance,
            LocalDate nextDueDate) {
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
        this.incentiveRate = incentiveRate;
        this.incentiveAmount = incentiveAmount;
        this.newBalance = newBalance;
        this.nextDueDate = nextDueDate;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getIncentiveRate() {
        return incentiveRate;
    }

    public void setIncentiveRate(BigDecimal incentiveRate) {
        this.incentiveRate = incentiveRate;
    }

    public BigDecimal getIncentiveAmount() {
        return incentiveAmount;
    }

    public void setIncentiveAmount(BigDecimal incentiveAmount) {
        this.incentiveAmount = incentiveAmount;
    }

    public BigDecimal getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(BigDecimal newBalance) {
        this.newBalance = newBalance;
    }

    public LocalDate getNextDueDate() {
        return nextDueDate;
    }

    public void setNextDueDate(LocalDate nextDueDate) {
        this.nextDueDate = nextDueDate;
    }

    public static FeePaymentBuilder builder() {
        return new FeePaymentBuilder();
    }

    public static class FeePaymentBuilder {
        private BigDecimal paymentAmount;
        private LocalDate paymentDate;
        private BigDecimal incentiveRate;
        private BigDecimal incentiveAmount;
        private BigDecimal newBalance;
        private LocalDate nextDueDate;

        public FeePaymentBuilder paymentAmount(BigDecimal paymentAmount) {
            this.paymentAmount = paymentAmount;
            return this;
        }

        public FeePaymentBuilder paymentDate(LocalDate paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }

        public FeePaymentBuilder incentiveRate(BigDecimal incentiveRate) {
            this.incentiveRate = incentiveRate;
            return this;
        }

        public FeePaymentBuilder incentiveAmount(BigDecimal incentiveAmount) {
            this.incentiveAmount = incentiveAmount;
            return this;
        }

        public FeePaymentBuilder newBalance(BigDecimal newBalance) {
            this.newBalance = newBalance;
            return this;
        }

        public FeePaymentBuilder nextDueDate(LocalDate nextDueDate) {
            this.nextDueDate = nextDueDate;
            return this;
        }

        public FeePayment build() {
            return new FeePayment(paymentAmount, paymentDate, incentiveRate, incentiveAmount, newBalance, nextDueDate);
        }
    }
}
