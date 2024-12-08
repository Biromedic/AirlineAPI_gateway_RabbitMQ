package com.example.airlineapi.controller;

import com.example.airlineapi.payload.FlightDTO;
import com.example.airlineapi.payload.QueryFlightRequestDTO;
import com.example.airlineapi.service.serviceInterface.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
@Tag(name = "Flights", description = "Flight Management APIs")
public class FlightController {

    private final FlightService flightService;

    @Operation(summary = "Create a new flight", description = "This endpoint allows admins to create new flights")
    @PostMapping("/admin/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FlightDTO> createFlight(@Valid @RequestBody FlightDTO flightDTO) {
        FlightDTO createdFlight = flightService.createFlight(flightDTO);
        return ResponseEntity.ok(createdFlight);
    }

    @Operation(summary = "Query flights", description = "Admin endpoint to get available flights")
    @GetMapping("/admin/available")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<FlightDTO>> getAvailableFlights() {
        List<FlightDTO> flights = flightService.getAvailableFlights();
        return ResponseEntity.ok(flights);
    }

    @PostMapping("/query")
    public ResponseEntity<Page<FlightDTO>> queryFlights(
            @Valid @RequestBody QueryFlightRequestDTO queryFlightRequestDTO,
            Pageable pageable
    ) {
        log.info("Querying flights with filters: {}", queryFlightRequestDTO);
        Page<FlightDTO> flights = flightService.queryFlights(queryFlightRequestDTO, pageable);
        return ResponseEntity.ok(flights);
    }

    @GetMapping("admin/{flightId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FlightDTO> getFlightById(@PathVariable Long flightId) {
        FlightDTO flight = flightService.getFlightById(flightId);
        return ResponseEntity.ok(flight);
    }
}
