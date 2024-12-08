package com.example.airlineapi.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListingDTO {
    @NotBlank(message = "Country is required")
    private String country;

    @NotBlank(message = "City is required")
    private String city;

    @Positive(message = "Number of people must be greater than 0")
    private int noOfPeople;

    @Positive(message = "Price must be greater than 0")
    private double price;
}
