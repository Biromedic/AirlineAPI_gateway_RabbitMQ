package com.example.airlineapi.payload;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookingDTO {

    @NotNull(message = "Listing ID is required")
    private Long listingId;

    @NotBlank(message = "Guest name is required")
    private String guestName;

    @FutureOrPresent(message = "From date must be today or in the future")
    private LocalDate fromDate;

    @FutureOrPresent(message = "To date must be in the future")
    private LocalDate toDate;

    @NotNull(message = "Number of people is required")
    private int noOfPeople;
}
