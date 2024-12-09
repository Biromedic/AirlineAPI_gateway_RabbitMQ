package com.example.paymentservice.service.interfaces;

import com.example.paymentservice.dto.NotificationDTO;

public interface NotificationProducerService {
    void sendNotification(NotificationDTO notification);
}
