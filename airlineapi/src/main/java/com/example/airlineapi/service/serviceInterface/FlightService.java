package com.example.airlineapi.service.serviceInterface;

import com.example.airlineapi.payload.FlightDTO;
import com.example.airlineapi.payload.QueryFlightRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface FlightService {
    FlightDTO createFlight(FlightDTO flightDTO);

    List<FlightDTO> getAvailableFlights();

    FlightDTO getFlightById(Long flightId);

    Page<FlightDTO> queryFlights(QueryFlightRequestDTO queryFlightRequestDTO, Pageable pageable);
}
