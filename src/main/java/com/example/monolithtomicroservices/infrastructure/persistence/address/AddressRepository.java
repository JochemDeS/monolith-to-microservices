package com.example.monolithtomicroservices.infrastructure.persistence.address;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    Optional<AddressEntity> findByStreetAndHouseNumberAndCityAndZipAndCountry(String street, String houseNumber, String city, String zip, String country);
}
