package com.example.airlineapi.controller;

import com.example.airlineapi.payload.TicketDTO;
import com.example.airlineapi.service.serviceInterface.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
@Tag(name = "Ticket API", description = "Operations related to ticket management")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/create-ticket")
    @Operation(summary = "Create a new ticket", description = "Creates a new ticket for a user and associates it with a flight.")
    public ResponseEntity<TicketDTO> createTicket(
            @Valid @RequestBody @Parameter(description = "Details of the ticket to be created", required = true) TicketDTO ticketDTO) {
        TicketDTO createdTicket = ticketService.createTicket(ticketDTO);
        return ResponseEntity.ok(createdTicket);
    }

    @PostMapping("/check-in/{ticketId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @Operation(summary = "Check-in a ticket", description = "Marks a ticket as checked-in if it hasn't been checked in already.")
    public ResponseEntity<TicketDTO> checkInTicket(
            @PathVariable @Parameter(description = "ID of the ticket to check-in", required = true) Long ticketId) {
        TicketDTO updatedTicket = ticketService.checkInTicket(ticketId);
        return ResponseEntity.ok(updatedTicket);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @Operation(summary = "Get tickets by user", description = "Retrieves all tickets associated with a specific user.")
    public ResponseEntity<List<TicketDTO>> getTicketsByUserId(
            @PathVariable @Parameter(description = "ID of the user whose tickets are to be retrieved", required = true) Long userId) {
        List<TicketDTO> tickets = ticketService.getTicketsByUserId(userId);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("admin/flight/{flightId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get tickets by flight", description = "Retrieves all tickets associated with a specific flight. Accessible only to admins.")
    public ResponseEntity<List<TicketDTO>> getTicketsByFlightId(
            @PathVariable @Parameter(description = "ID of the flight whose tickets are to be retrieved", required = true) Long flightId) {
        List<TicketDTO> tickets = ticketService.getTicketsByFlightId(flightId);
        return ResponseEntity.ok(tickets);
    }
}
