package com.example.paymentservice.dto;

import lombok.Data;

@Data
public class NotificationDTO {
    private String recipient;
    private String message;
}
