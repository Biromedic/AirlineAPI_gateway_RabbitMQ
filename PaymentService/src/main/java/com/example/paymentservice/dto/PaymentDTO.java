package com.example.paymentservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO implements Serializable {
    @NotNull
    private String transactionId;

    @NotNull
    private Double amount;

    @NotNull
    private String currency;

    @NotNull
    private String userId;
}
