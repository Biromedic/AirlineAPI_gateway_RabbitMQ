package com.example.notificationservice.service.impl;

import com.example.notificationservice.dto.NotificationDTO;
import com.example.notificationservice.service.interfaces.NotificationProducerService;
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
    }
}
