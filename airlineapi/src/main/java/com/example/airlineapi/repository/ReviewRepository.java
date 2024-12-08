package com.example.airlineapi.repository;

import com.example.airlineapi.model.Booking;
import com.example.airlineapi.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByBooking(Booking booking);
}
