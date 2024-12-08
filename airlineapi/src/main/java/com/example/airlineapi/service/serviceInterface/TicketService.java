package com.example.airlineapi.service.serviceInterface;

import com.example.airlineapi.model.Ticket;
import com.example.airlineapi.payload.TicketDTO;

import java.util.List;

public interface TicketService {

    TicketDTO createTicket(TicketDTO ticketDTO);

    List<TicketDTO> getTicketsByUserId(Long userId);

    List<TicketDTO> getTicketsByFlightId(Long flightId);

    TicketDTO checkInTicket(Long ticketId);
}