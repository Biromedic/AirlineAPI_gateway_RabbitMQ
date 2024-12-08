package com.example.airlineapi.service.impl;

import com.example.airlineapi.exception.ResourceNotFoundException;
import com.example.airlineapi.model.Flight;
import com.example.airlineapi.payload.FlightDTO;
import com.example.airlineapi.payload.QueryFlightRequestDTO;
import com.example.airlineapi.repository.FlightRepository;
import com.example.airlineapi.service.serviceInterface.FlightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final ModelMapper modelMapper;

    @Override
    public FlightDTO createFlight(FlightDTO flightDTO) {
        Flight flight = modelMapper.map(flightDTO, Flight.class);
        Flight savedFlight = flightRepository.save(flight);
        return modelMapper.map(savedFlight, FlightDTO.class);
    }

    @Override
    public List<FlightDTO> getAvailableFlights() {
        return flightRepository.findByCapacityGreaterThan(0)
                .stream()
                .map(flight -> modelMapper.map(flight, FlightDTO.class))
                .toList();
    }

    @Override
    public FlightDTO getFlightById(Long flightId) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found"));
        return modelMapper.map(flight, FlightDTO.class);
    }

    @Override
    public Page<FlightDTO> queryFlights(QueryFlightRequestDTO queryFlightRequestDTO, Pageable pageable) {
        Page<Flight> flights = flightRepository.findByFilters(
                queryFlightRequestDTO.getFromLocation(),
                queryFlightRequestDTO.getToLocation(),
                queryFlightRequestDTO.getStartDate(),
                queryFlightRequestDTO.getEndDate(),
                pageable
        );

        return flights.map(flight -> modelMapper.map(flight, FlightDTO.class));
    }


}