package com.example.monolithtomicroservices.application.customer;

import com.example.monolithtomicroservices.domain.Address;
import com.example.monolithtomicroservices.domain.Name;

public record RegisterCustomer(Name customer, Address address, String email) {
    private RegisterCustomer(Builder builder) {
        this(builder.customer, builder.address, builder.email);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Name customer;
        private Address address;
        private String email;

        public Builder customer(Name customer) {
            this.customer = customer;
            return this;
        }

        public Builder address(Address address) {
            this.address = address;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public RegisterCustomer build() {
            return new RegisterCustomer(this);
        }
    }
}
