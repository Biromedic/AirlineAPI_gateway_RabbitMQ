package com.example.notificationservice.service.interfaces;

import com.example.notificationservice.dto.NotificationDTO;

public interface NotificationProducerService {

    void sendNotification(NotificationDTO notification);
}
