package com.example.notificationservice.service.interfaces;

import com.example.notificationservice.dto.NotificationDTO;

public interface NotificationConsumerService {

    void receiveNotification(NotificationDTO notification);
}
