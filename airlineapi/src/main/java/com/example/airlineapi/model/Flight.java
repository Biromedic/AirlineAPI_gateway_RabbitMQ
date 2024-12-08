package com.example.airlineapi.model;

import com.example.airlineapi.model.enums.FlightDays;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private Long flightId;

    @NotBlank
    @Column(name = "from_location", nullable = false)
    private String fromLocation;

    @NotBlank
    @Column(name = "to_location", nullable = false)
    private String toLocation;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @NotNull
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @ElementCollection(targetClass = FlightDays.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "flight_days", joinColumns = @JoinColumn(name = "flight_id"))
    @Column(name = "day_of_week", nullable = false)
    private Set<FlightDays> daysOfWeek;

    @NotNull
    @Column(name = "capacity", nullable = false)
    private int capacity;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Ticket> tickets = new HashSet<>();
}
