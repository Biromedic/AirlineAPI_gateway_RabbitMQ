package com.example.paymentservice.service.impl;

import com.example.paymentservice.dto.PaymentDTO;
import com.example.paymentservice.service.interfaces.PaymentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void processPayment(PaymentDTO paymentDTO) {
        logger.info("Processing payment: {}", paymentDTO);
        rabbitTemplate.convertAndSend("payment-queue", paymentDTO);
        logger.info("Payment processed successfully");
    }
}
