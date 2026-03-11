package com.leedtech.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Payment Service.
 * This service handles processing of fee payments for students.
 */
@SpringBootApplication
public class PaymentApplication {

	private static final Logger logger = LoggerFactory.getLogger(PaymentApplication.class);

	public static void main(String[] args) {
		logger.info("Starting Payment Application...");
		SpringApplication.run(PaymentApplication.class, args);
		logger.info("Payment Application started successfully.");
	}

}
