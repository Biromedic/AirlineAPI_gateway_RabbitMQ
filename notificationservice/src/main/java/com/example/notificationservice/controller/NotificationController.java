package com.example.notificationservice.controller;


import com.example.notificationservice.dto.NotificationDTO;
import com.example.notificationservice.service.interfaces.NotificationProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationProducerService notificationProducer;

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationDTO notification) {
        notificationProducer.sendNotification(notification);
        return ResponseEntity.ok("Notification sent successfully!");
    }
}
