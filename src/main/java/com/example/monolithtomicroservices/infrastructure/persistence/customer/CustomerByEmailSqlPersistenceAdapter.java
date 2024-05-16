package com.example.monolithtomicroservices.infrastructure.persistence.customer;

import com.example.monolithtomicroservices.application.customer.GetCustomerByEmailPort;
import com.example.monolithtomicroservices.application.customer.SaveCustomerPort;
import com.example.monolithtomicroservices.domain.*;
import com.example.monolithtomicroservices.infrastructure.persistence.address.AddressEntity;
import com.example.monolithtomicroservices.infrastructure.persistence.address.AddressSqlPersistenceAdapter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerByEmailSqlPersistenceAdapter implements GetCustomerByEmailPort, SaveCustomerPort {
    private final CustomerRepository customerRepository;
    private final AddressSqlPersistenceAdapter addressSqlPersistenceAdapter;

    public CustomerByEmailSqlPersistenceAdapter(CustomerRepository customerRepository, AddressSqlPersistenceAdapter addressSqlPersistenceAdapter) {
        this.customerRepository = customerRepository;
        this.addressSqlPersistenceAdapter = addressSqlPersistenceAdapter;
    }

    @Override
    public Optional<Customer> byEmail(String email) {
        return customerRepository.findByEmail(email)
                .map(this::mapToCustomer);
    }

    @Override
    public Customer save(Customer customer) {
        final var address = addressSqlPersistenceAdapter.save(customer.address());
        final var customerEntity = mapToCustomerEntity(customer);
        customerEntity.setAddress(address);

        return mapToCustomer(customerRepository.save(customerEntity));
    }

    private Customer mapToCustomer(CustomerEntity customerEntity) {
        return Customer.builder()
                .id(CustomerId.builder()
                        .id(customerEntity.getId())
                        .build())
                .name(Name.builder()
                        .firstName(customerEntity.getFirstName())
                        .lastName(customerEntity.getLastName())
                        .build())
                .address(Address.builder()
                        .id(AddressId.builder()
                                .id(customerEntity.getAddress().getId())
                                .build())
                        .street(customerEntity.getAddress().getStreet())
                        .houseNumber(customerEntity.getAddress().getHouseNumber())
                        .city(customerEntity.getAddress().getCity())
                        .zip(customerEntity.getAddress().getZip())
                        .country(customerEntity.getAddress().getCountry())
                        .build())
                .email(customerEntity.getEmail())
                .build();
    }

    private CustomerEntity mapToCustomerEntity(Customer customer) {
        return CustomerEntity.builder()
                .firstName(customer.name().firstName())
                .lastName(customer.name().lastName())
                .address(AddressEntity.builder()
                        .street(customer.address().street())
                        .houseNumber(customer.address().houseNumber())
                        .city(customer.address().city())
                        .zip(customer.address().zip())
                        .country(customer.address().country())
                        .build())
                .email(customer.email())
                .build();
    }
}
