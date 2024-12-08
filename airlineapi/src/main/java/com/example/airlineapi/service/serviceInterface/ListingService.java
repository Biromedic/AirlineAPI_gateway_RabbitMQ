package com.example.airlineapi.service.serviceInterface;

import com.example.airlineapi.payload.ListingDTO;
import com.example.airlineapi.payload.QueryListingRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ListingService {
    ListingDTO createListing(ListingDTO listingDTO);

    Page<ListingDTO> queryListings(QueryListingRequestDTO queryRequest, Pageable pageable);

    List<ListingDTO> getAdminReport(String country, String city);
}