package com.example.airlineapi.service.impl;

import com.example.airlineapi.exception.ResourceNotFoundException;
import com.example.airlineapi.model.Booking;
import com.example.airlineapi.model.Listing;
import com.example.airlineapi.payload.BookingDTO;
import com.example.airlineapi.repository.BookingRepository;
import com.example.airlineapi.repository.ListingRepository;
import com.example.airlineapi.service.serviceInterface.BookingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final ListingRepository listingRepository;
    private final ModelMapper modelMapper;

    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        // Validate Listing
        Listing listing = listingRepository.findById(bookingDTO.getListingId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Listing with ID %d not found", bookingDTO.getListingId())));

        // Validate Dates
        if (bookingDTO.getFromDate().isAfter(bookingDTO.getToDate())) {
            throw new IllegalArgumentException("From date must be before or equal to To date");
        }

        // Map DTO to Entity
        Booking booking = Booking.builder()
                .listing(listing)
                .guestName(bookingDTO.getGuestName())
                .fromDate(bookingDTO.getFromDate())
                .toDate(bookingDTO.getToDate())
                .noOfPeople(bookingDTO.getNoOfPeople())
                .build();

        Booking savedBooking = bookingRepository.save(booking);

        return modelMapper.map(savedBooking, BookingDTO.class);
    }
}
