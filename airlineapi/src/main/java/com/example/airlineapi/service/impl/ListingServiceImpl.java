package com.example.airlineapi.service.impl;

import com.example.airlineapi.model.Listing;
import com.example.airlineapi.payload.ListingDTO;
import com.example.airlineapi.payload.QueryListingRequestDTO;
import com.example.airlineapi.repository.ListingRepository;
import com.example.airlineapi.service.serviceInterface.ListingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListingServiceImpl implements ListingService {

    private final ListingRepository listingRepository;
    private final ModelMapper modelMapper;

    @Override
    public ListingDTO createListing(ListingDTO listingDTO) {

        Listing listing = modelMapper.map(listingDTO, Listing.class);

        Listing savedListing = listingRepository.save(listing);

        return modelMapper.map(savedListing, ListingDTO.class);
    }

    @Override
    public Page<ListingDTO> queryListings(QueryListingRequestDTO queryRequest, Pageable pageable) {
        Page<Listing> listings = listingRepository.findByCityAndCountryAndNoOfPeopleGreaterThanEqual(
                queryRequest.getCity(),
                queryRequest.getCountry(),
                queryRequest.getNoOfPeople(),
                pageable
        );

        return listings.map(listing -> modelMapper.map(listing, ListingDTO.class));
    }

    @Override
    public List<ListingDTO> getAdminReport(String country, String city) {
        List<Listing> listings = listingRepository.findByCountryAndCity(country, city);
        return listings.stream()
                .map(listing -> modelMapper.map(listing, ListingDTO.class))
                .toList();
    }

}
