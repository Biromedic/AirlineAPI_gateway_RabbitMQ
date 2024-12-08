package com.example.airlineapi.controller;

import com.example.airlineapi.payload.ListingDTO;
import com.example.airlineapi.payload.QueryListingRequestDTO;
import com.example.airlineapi.service.serviceInterface.ListingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listings")
@RequiredArgsConstructor
@Tag(name = "Listings", description = "Listing management for hosts and guests")
public class ListingController {

    private final ListingService listingService;

    @Operation(summary = "Create a new listing")
    @PostMapping("/create")
    @PreAuthorize("hasRole('HOST')")
    public ResponseEntity<ListingDTO> createListing(@Valid @RequestBody ListingDTO listingDTO) {
        ListingDTO createdListing = listingService.createListing(listingDTO);
        return ResponseEntity.ok(createdListing);
    }

    @Operation(summary = "Query listings", description = "Guests can query listings based on filters")
    @PostMapping("/query")
    public ResponseEntity<Page<ListingDTO>> queryListings(
            @Valid @RequestBody QueryListingRequestDTO queryRequest,
            Pageable pageable) {
        Page<ListingDTO> listings = listingService.queryListings(queryRequest, pageable);
        return ResponseEntity.ok(listings);
    }

    @Operation(summary = "Admin report for listings", description = "Admins can get a report of listings filtered by country and city")
    @GetMapping("/admin/report")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ListingDTO>> getAdminReport(
            @RequestParam String country,
            @RequestParam String city) {
        List<ListingDTO> listings = listingService.getAdminReport(country, city);
        return ResponseEntity.ok(listings);
    }
}
