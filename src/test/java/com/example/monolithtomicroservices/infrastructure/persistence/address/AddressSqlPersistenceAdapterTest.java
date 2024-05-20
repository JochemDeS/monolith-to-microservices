package com.example.monolithtomicroservices.infrastructure.persistence.address;

import com.example.monolithtomicroservices.domain.Address;
import com.example.monolithtomicroservices.infrastructure.persistence.PersistenceTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;


@PersistenceTest
class AddressSqlPersistenceAdapterTest {
    @Autowired
    private AddressRepository addressRepository;

    @Test
    void shouldSaveExpectingFields() {
        final var address = Address.builder()
                .street("street")
                .houseNumber("houseNumber")
                .city("city")
                .zip("zip")
                .country("country")
                .build();

        final var expected = AddressEntity.builder()
                .street(address.street())
                .houseNumber(address.houseNumber())
                .city(address.city())
                .zip(address.zip())
                .country(address.country())
                .build();

        final var target = new AddressSqlPersistenceAdapter(addressRepository);
        final var result = target.save(address);

        assertThat(result).isNotNull();
        assertThat(result.getStreet()).isEqualTo(expected.getStreet());
        assertThat(result.getHouseNumber()).isEqualTo(expected.getHouseNumber());
        assertThat(result.getCity()).isEqualTo(expected.getCity());
        assertThat(result.getZip()).isEqualTo(expected.getZip());
        assertThat(result.getCountry()).isEqualTo(expected.getCountry());
    }

    @Test
    void shouldRetrieveExistingAddressInsteadOfCreatingNew() {
        final var address = Address.builder()
                .street("street")
                .houseNumber("houseNumber")
                .city("city")
                .zip("zip")
                .country("country")
                .build();

        final var expected = addressRepository.save(AddressEntity.builder()
                .street(address.street())
                .houseNumber(address.houseNumber())
                .city(address.city())
                .zip(address.zip())
                .country(address.country())
                .build());

        final var target = new AddressSqlPersistenceAdapter(addressRepository);
        final var result = target.save(address);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(expected.getId());
    }
}