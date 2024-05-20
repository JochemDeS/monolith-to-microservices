package com.example.monolithtomicroservices.infrastructure.persistence.customer;

import com.example.monolithtomicroservices.domain.Address;
import com.example.monolithtomicroservices.domain.Customer;
import com.example.monolithtomicroservices.domain.Name;
import com.example.monolithtomicroservices.infrastructure.persistence.PersistenceTest;
import com.example.monolithtomicroservices.infrastructure.persistence.address.AddressEntity;
import com.example.monolithtomicroservices.infrastructure.persistence.address.AddressSqlPersistenceAdapter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import static org.assertj.core.api.Assertions.assertThat;


@PersistenceTest
@ComponentScan(basePackageClasses = AddressSqlPersistenceAdapter.class)
class CustomerSqlPersistenceAdapterTest {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressSqlPersistenceAdapter addressSqlPersistenceAdapter;

    @Test
    void shouldFindCustomerByEmail() {
        final var target = new CustomerSqlPersistenceAdapter(customerRepository, addressSqlPersistenceAdapter);
        final var expected = CustomerEntity.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@test.com")
                .address(AddressEntity.builder()
                        .street("street")
                        .houseNumber("houseNumber")
                        .city("city")
                        .zip("zip")
                        .country("country")
                        .build())
                .build();
        customerRepository.save(expected);

        final var result = target.byEmail(expected.getEmail());

        assertThat(result).isPresent();
        assertThat(result.get().email()).isEqualTo(expected.getEmail());
    }

    @Test
    void shouldReturnEmptyWhenCustomerNotFound() {
        final var target = new CustomerSqlPersistenceAdapter(customerRepository, addressSqlPersistenceAdapter);
        final var result = target.byEmail("nobody@test.com");

        assertThat(result).isEmpty();
    }

    @Test
    void shouldSaveCustomer() {
        final var target = new CustomerSqlPersistenceAdapter(customerRepository, addressSqlPersistenceAdapter);
        final var expected = Customer.builder()
                .name(Name.builder()
                        .firstName("John")
                        .lastName("Doe")
                        .build())
                .email("john.doe@test.com")
                .address(Address.builder()
                        .street("street")
                        .houseNumber("houseNumber")
                        .city("city")
                        .zip("zip")
                        .country("country")
                        .build())
                .build();

        final var result = target.save(expected);

        assertThat(result).isNotNull();
        assertThat(result.id()).isNotNull();
        assertThat(result.name().firstName()).isEqualTo(expected.name().firstName());
        assertThat(result.name().lastName()).isEqualTo(expected.name().lastName());
        assertThat(result.email()).isEqualTo(expected.email());

        assertThat(result.address().id()).isNotNull();
        assertThat(result.address().street()).isEqualTo(expected.address().street());
        assertThat(result.address().houseNumber()).isEqualTo(expected.address().houseNumber());
        assertThat(result.address().city()).isEqualTo(expected.address().city());
        assertThat(result.address().zip()).isEqualTo(expected.address().zip());
        assertThat(result.address().country()).isEqualTo(expected.address().country());
    }
}