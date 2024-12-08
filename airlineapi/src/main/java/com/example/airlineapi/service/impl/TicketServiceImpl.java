package com.example.airlineapi.service.impl;

import com.example.airlineapi.model.enums.TicketStatus;
import com.example.airlineapi.payload.TicketDTO;
import com.example.airlineapi.exception.ResourceNotFoundException;
import com.example.airlineapi.model.Flight;
import com.example.airlineapi.model.Ticket;
import com.example.airlineapi.model.User;
import com.example.airlineapi.repository.FlightRepository;
import com.example.airlineapi.repository.TicketRepository;
import com.example.airlineapi.service.serviceInterface.TicketService;
import com.example.airlineapi.service.serviceInterface.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final FlightRepository flightRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    public TicketDTO createTicket(TicketDTO ticketDTO) {
        Flight flight = flightRepository.findById(ticketDTO.getFlightId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Flight with ID %d not found", ticketDTO.getFlightId())));

        if (flight.getCapacity() <= 0) {
            throw new IllegalStateException("No seats available for this flight.");
        }

        User user = userService.getUserById(ticketDTO.getUserId());
        flight.setCapacity(flight.getCapacity() - 1);

        Ticket ticket = Ticket.builder()
                .user(user)
                .flight(flight)
                .passengerFullname(ticketDTO.getPassengerFullname())
                .status(TicketStatus.BOOKED)
                .bookingDate(LocalDate.now())
                .build();

        Ticket savedTicket = ticketRepository.save(ticket);

        return modelMapper.map(savedTicket, TicketDTO.class);
    }


    @Override
    public List<TicketDTO> getTicketsByUserId(Long userId) {
        List<Ticket> tickets = ticketRepository.findByUser_UserId(userId);
        return tickets.stream()
                .map(ticket -> modelMapper.map(ticket, TicketDTO.class))
                .toList();
    }

    @Override
    public List<TicketDTO> getTicketsByFlightId(Long flightId) {
        return ticketRepository.findByFlight_FlightId(flightId).stream()
                .map(ticket -> modelMapper.map(ticket, TicketDTO.class))
                .toList();
    }

    @Override
    public TicketDTO checkInTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Ticket with ID %d not found", ticketId)));

        if (ticket.getStatus() == TicketStatus.CHECKED_IN) {
            throw new IllegalStateException("This ticket is already checked in.");
        }

        ticket.setStatus(TicketStatus.CHECKED_IN);
        Ticket updatedTicket = ticketRepository.save(ticket);

        return modelMapper.map(updatedTicket, TicketDTO.class);
    }
}
