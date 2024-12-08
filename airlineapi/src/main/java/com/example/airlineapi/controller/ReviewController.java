package com.example.airlineapi.controller;

import com.example.airlineapi.payload.ReviewDTO;
import com.example.airlineapi.service.serviceInterface.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@Tag(name = "Reviews", description = "Endpoints for managing reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "Create a review", description = "Allows User to leave a review after a booking")
    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ReviewDTO> createReview(@Valid @RequestBody ReviewDTO reviewDTO) {
        ReviewDTO createdReview = reviewService.createReview(reviewDTO);
        return ResponseEntity.ok(createdReview);
    }
}
