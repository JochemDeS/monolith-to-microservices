package com.example.monolithtomicroservices.domain;

public record Address(AddressId id, String street, String houseNumber, String city, String zip, String country) {

    private Address(Builder builder) {
        this(builder.id, builder.street, builder.houseNumber, builder.city, builder.zip, builder.country);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private AddressId id;
        private String street;
        private String houseNumber;
        private String city;
        private String zip;
        private String country;

        public Builder id(AddressId id) {
            this.id = id;
            return this;
        }

        public Builder street(String street) {
            this.street = street;
            return this;
        }

        public Builder houseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder zip(String zipCode) {
            this.zip = zipCode;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}
