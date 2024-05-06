package com.example.monolithtomicroservices.infrastructure.persistence.customer;

import com.example.monolithtomicroservices.application.customer.SaveCustomerPort;
import com.example.monolithtomicroservices.domain.*;
import com.example.monolithtomicroservices.infrastructure.persistence.address.AddressEntity;
import com.example.monolithtomicroservices.infrastructure.persistence.address.AddressSqlPersistenceAdapter;
import org.springframework.stereotype.Component;

@Component
public class CustomerSqlPersistenceAdapter implements SaveCustomerPort {
    private final CustomerRepository customerRepository;
    private final AddressSqlPersistenceAdapter addressSqlPersistenceAdapter;

    public CustomerSqlPersistenceAdapter(CustomerRepository customerRepository, AddressSqlPersistenceAdapter addressSqlPersistenceAdapter) {
        this.customerRepository = customerRepository;
        this.addressSqlPersistenceAdapter = addressSqlPersistenceAdapter;
    }

    @Override
    public Customer save(Customer customer) {
        customerRepository.findByEmail(customer.email()).ifPresent(customerEntity -> {
                    throw new IllegalArgumentException("Customer already exists");
        });

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
