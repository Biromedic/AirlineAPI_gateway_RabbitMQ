package com.example.airlineapi.payload;

import com.example.airlineapi.model.enums.FlightDays;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class FlightDTO {

    @Positive(message = "Flight ID must be a positive number")
    private Long id;

    @NotBlank(message = "From location is required")
    private String fromLocation;

    @NotBlank(message = "To location is required")
    private String toLocation;

    @FutureOrPresent(message = "Start date must be today or in the future")
    private LocalDate startDate;

    @FutureOrPresent(message = "End date must be in the future")
    private LocalDate endDate;

    @NotEmpty(message = "Days of the week must not be empty")
    private Set<FlightDays> daysOfWeek;

    @Positive(message = "Capacity must be a positive number")
    private int capacity;
}