package com.example.airlineapi.service.serviceInterface;

import com.example.airlineapi.payload.BookingDTO;

public interface BookingService {
    BookingDTO createBooking(BookingDTO bookingDTO);
}
