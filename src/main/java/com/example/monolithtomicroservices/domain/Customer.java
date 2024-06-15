package com.example.monolithtomicroservices.domain;

public record Customer(CustomerId id, Name name, Address address, String email, Cart cart) {
    private Customer(Builder builder) {
        this(builder.id, builder.name, builder.address, builder.email, builder.cart);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private CustomerId id;
        private Name name;
        private Address address;
        private String email;
        private Cart cart;

        public Builder id(CustomerId id) {
            this.id = id;
            return this;
        }

        public Builder name(Name name) {
            this.name = name;
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

        public Builder cart(Cart cart) {
            this.cart = cart;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
