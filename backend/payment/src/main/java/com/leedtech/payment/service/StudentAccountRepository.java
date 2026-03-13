package com.leedtech.payment.service;

import com.leedtech.payment.domain.StudentAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * In-memory repository for storing student account details.
 * This class serves as a mock for a real database implementation.
 */
@Repository
public class StudentAccountRepository {
    private static final Logger logger = LoggerFactory.getLogger(StudentAccountRepository.class);
    private final Map<String, StudentAccount> accounts = new HashMap<>();

    public StudentAccountRepository() {
        // Initializing mock data
        logger.debug("Initializing StudentAccountRepository with mock data.");
        accounts.put("STU001", new StudentAccount("STU001", new BigDecimal("1000000.00")));
        accounts.put("STU002", new StudentAccount("STU002", new BigDecimal("500000.00")));
        accounts.put("STU003", new StudentAccount("STU003", new BigDecimal("100000.00")));
    }

    // Finds a student account by student number.
    public Optional<StudentAccount> findByStudentNumber(String studentNumber) {
        logger.debug("Finding account for student number: {}", studentNumber);
        return Optional.ofNullable(accounts.get(studentNumber));
    }

    /**
     * Saves or updates a student account in the in-memory map.
     * @param account The StudentAccount to save.
     */
    public void save(StudentAccount account) {
        logger.debug("Saving account for student number: {}", account.getStudentNumber());
        accounts.put(account.getStudentNumber(), account);
    }

}
