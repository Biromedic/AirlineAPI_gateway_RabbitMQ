package com.example.paymentservice.service.impl;

import com.example.paymentservice.config.RabbitMQConfig;
import com.example.paymentservice.dto.NotificationDTO;
import com.example.paymentservice.dto.PaymentDTO;
import com.example.paymentservice.service.interfaces.NotificationProducerService;
import com.example.paymentservice.service.interfaces.PaymentConsumerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentConsumerServiceImpl implements PaymentConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentConsumerServiceImpl.class);
    private final NotificationProducerService notificationProducerService;

    @Override
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receivePayment(PaymentDTO paymentDTO) {
        logger.info("Received payment: {}", paymentDTO);
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setRecipient(paymentDTO.getUserId());
        notificationDTO.setMessage("Your payment of type " + paymentDTO.getAmount() + " has been processed successfully.");

        notificationProducerService.sendNotification(notificationDTO);
    }
}
