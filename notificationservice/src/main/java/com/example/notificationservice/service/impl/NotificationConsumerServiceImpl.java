package com.example.notificationservice.service.impl;

import com.example.notificationservice.config.RabbitMQConfig;
import com.example.notificationservice.dto.NotificationDTO;
import com.example.notificationservice.service.interfaces.NotificationConsumerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumerServiceImpl implements NotificationConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationConsumerServiceImpl.class);

    @Override
    @RabbitListener(queues = RabbitMQConfig.NOTIFICATION_QUEUE)
    public void receiveNotification(NotificationDTO notification) {
        try {
            Thread.sleep(5000);
            logger.info("Processed Notification after delay: Recipient: {}, Message: {}",
                    notification.getRecipient(),
                    notification.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Error while processing notification: {}", e.getMessage());
        }
    }
}
