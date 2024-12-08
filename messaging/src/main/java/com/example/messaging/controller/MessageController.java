package com.example.messaging.controller;

import com.example.messaging.dto.MessageDTO;
import com.example.messaging.service.MessageProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageProducerService messageProducer;

    public MessageController(MessageProducerService messageProducer) {
        this.messageProducer = messageProducer;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody MessageDTO messageDTO) {
        String messageContent = messageDTO.getContent();
        messageProducer.sendMessage(messageContent);
        return ResponseEntity.ok("Message sent: " + messageContent);
    }
}
