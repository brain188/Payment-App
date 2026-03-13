package com.leedtech.payment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leedtech.payment.dto.PaymentRequest;
import com.leedtech.payment.dto.PaymentResponse;
import com.leedtech.payment.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testProcessPayment_Success() throws Exception {
        PaymentRequest request = new PaymentRequest("STU001", new BigDecimal("1000.00"), LocalDate.now());

        mockMvc.perform(post("/one-time-fee-payment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentNumber").value("STU001"))
                .andExpect(jsonPath("$.paymentAmount").value(1000.00));
    }

    @Test
    void testProcessPayment_ValidationFailure() throws Exception {
        PaymentRequest request = new PaymentRequest("STU001", new BigDecimal("0.00"), LocalDate.now());

        mockMvc.perform(post("/one-time-fee-payment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testProcessPayment_ValidationFailure_Negative() throws Exception {
        PaymentRequest request = new PaymentRequest("STU001", new BigDecimal("-100.00"), LocalDate.now());

        mockMvc.perform(post("/one-time-fee-payment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
