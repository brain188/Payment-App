package com.leedtech.payment.domain;

import java.math.BigDecimal;

/**
 * Domain model representing a student's account and balance.
 */
public class StudentAccount {
    private String studentNumber;
    private BigDecimal balance;

    public StudentAccount() {}

    public StudentAccount(String studentNumber, BigDecimal balance) {
        this.studentNumber = studentNumber;
        this.balance = balance;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public static StudentAccountBuilder builder() {
        return new StudentAccountBuilder();
    }

    public static class StudentAccountBuilder {
        private String studentNumber;
        private BigDecimal balance;

        public StudentAccountBuilder studentNumber(String studentNumber) {
            this.studentNumber = studentNumber;
            return this;
        }

        public StudentAccountBuilder balance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public StudentAccount build() {
            return new StudentAccount(studentNumber, balance);
        }
    }
}
