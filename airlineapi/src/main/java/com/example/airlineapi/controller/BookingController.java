package com.example.airlineapi.controller;

import com.example.airlineapi.payload.BookingDTO;
import com.example.airlineapi.service.serviceInterface.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
@Tag(name = "Bookings", description = "Endpoints for managing bookings")
public class BookingController {

    private final BookingService bookingService;

    @Operation(summary = "Create a booking", description = "Allows guests to book a stay")
    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BookingDTO> createBooking(@Valid @RequestBody BookingDTO bookingDTO) {
        BookingDTO createdBooking = bookingService.createBooking(bookingDTO);
        return ResponseEntity.ok(createdBooking);
    }
}
