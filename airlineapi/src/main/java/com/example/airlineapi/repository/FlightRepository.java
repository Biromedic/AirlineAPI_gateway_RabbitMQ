package com.example.airlineapi.repository;

import com.example.airlineapi.model.Flight;
import com.example.airlineapi.model.enums.FlightDays;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByCapacityGreaterThan(int capacity);

    @Query("SELECT f FROM Flight f WHERE " +
            "f.fromLocation = :from AND " +
            "f.toLocation = :to AND " +
            "f.startDate >= :startDate AND " +
            "f.endDate <= :endDate ")
    Page<Flight> findByFilters(String from, String to, LocalDate startDate, LocalDate endDate, Pageable pageable);

}
