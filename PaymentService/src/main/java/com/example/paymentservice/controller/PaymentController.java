package com.example.paymentservice.controller;

import com.example.paymentservice.dto.PaymentDTO;
import com.example.paymentservice.service.interfaces.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/process")
    public ResponseEntity<String> processPayment(@Valid @RequestBody PaymentDTO paymentDTO) {
        paymentService.processPayment(paymentDTO);
        return ResponseEntity.ok("Payment processed successfully for transaction ID: " + paymentDTO.getTransactionId());
    }
}
