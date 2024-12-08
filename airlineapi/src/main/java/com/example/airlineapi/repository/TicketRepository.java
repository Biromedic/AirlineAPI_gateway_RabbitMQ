package com.example.airlineapi.repository;

import com.example.airlineapi.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByUser_UserId(Long userId);
    List<Ticket> findByFlight_FlightId(Long flightId);
}