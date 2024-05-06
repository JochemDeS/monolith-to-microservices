package com.example.monolithtomicroservices.infrastructure.persistence.address;

import com.example.monolithtomicroservices.domain.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressSqlPersistenceAdapter {
    private final AddressRepository addressRepository;

    public AddressSqlPersistenceAdapter(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public AddressEntity save(Address address) {
        return addressRepository.findByStreetAndHouseNumberAndCityAndZipAndCountry(
                address.street(),
                address.houseNumber(),
                address.city(),
                address.zip(),
                address.country()
        ).orElseGet(() -> addressRepository.save(mapToAddressEntity(address)));
    }

    private AddressEntity mapToAddressEntity(Address address) {
        return new AddressEntity.Builder()
                .street(address.street())
                .houseNumber(address.houseNumber())
                .city(address.city())
                .zip(address.zip())
                .country(address.country())
                .build();
    }
}
