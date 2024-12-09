package com.example.paymentservice.service.interfaces;

import com.example.paymentservice.dto.PaymentDTO;

public interface PaymentConsumerService {

    void receivePayment(PaymentDTO paymentDTO);
}
