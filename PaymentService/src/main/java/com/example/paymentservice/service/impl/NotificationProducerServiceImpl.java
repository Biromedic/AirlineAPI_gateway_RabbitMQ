package com.example.paymentservice.service.impl;

import com.example.paymentservice.dto.NotificationDTO;
import com.example.paymentservice.service.interfaces.NotificationProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationProducerServiceImpl implements NotificationProducerService {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendNotification(NotificationDTO notification) {
        rabbitTemplate.convertAndSend("notification.queue", notification);
        System.out.println("Notification sent: " + notification);
    }
}
