package com.example.paymentservice.service.interfaces;

import com.example.paymentservice.dto.PaymentDTO;

public interface PaymentService {
    void processPayment(PaymentDTO paymentDTO);
}
