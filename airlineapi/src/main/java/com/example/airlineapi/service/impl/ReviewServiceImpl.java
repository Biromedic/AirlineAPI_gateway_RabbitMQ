package com.example.airlineapi.service.impl;

import com.example.airlineapi.exception.ResourceNotFoundException;
import com.example.airlineapi.model.Booking;
import com.example.airlineapi.model.Review;
import com.example.airlineapi.payload.ReviewDTO;
import com.example.airlineapi.repository.BookingRepository;
import com.example.airlineapi.repository.ReviewRepository;
import com.example.airlineapi.service.serviceInterface.ReviewService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;

    @Override
    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        Booking booking = bookingRepository.findById(reviewDTO.getBookingId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Booking with ID %d not found", reviewDTO.getBookingId())));

        if (reviewRepository.existsByBooking(booking)) {
            throw new IllegalStateException("A review already exists for this booking.");
        }

        Review review = Review.builder()
                .booking(booking)
                .rating(reviewDTO.getRating())
                .comment(reviewDTO.getComment())
                .build();

        Review savedReview = reviewRepository.save(review);

        return modelMapper.map(savedReview, ReviewDTO.class);
    }
}
