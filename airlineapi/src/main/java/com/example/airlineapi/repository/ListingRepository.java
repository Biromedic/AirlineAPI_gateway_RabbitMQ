package com.example.airlineapi.repository;

import com.example.airlineapi.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {
    Page<Listing> findByCityAndCountryAndNoOfPeopleGreaterThanEqual(
            String city, String country, int noOfPeople, Pageable pageable);

    List<Listing> findByCountryAndCity(String country, String city);

}
