package com.example.airlineapi.payload;

import com.example.airlineapi.model.enums.TicketStatus;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDTO {

    @NotNull(message = "Flight ID is required")
    private Long flightId;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotBlank(message = "Passenger fullname is required")
    @Size(max = 100, message = "Passenger fullname must not exceed 100 characters")
    private String passengerFullname;

    private TicketStatus status;
}
