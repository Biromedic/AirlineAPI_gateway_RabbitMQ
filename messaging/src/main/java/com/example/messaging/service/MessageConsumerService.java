package com.example.messaging.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumerService {

    @RabbitListener(queues = "messageQueue")
    public void receiveMessage(String message) {
        System.out.println("Message received: " + message);
    }
}
