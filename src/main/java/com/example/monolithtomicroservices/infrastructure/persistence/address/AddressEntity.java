package com.example.monolithtomicroservices.infrastructure.persistence.address;

import jakarta.persistence.*;

@Entity(name = "Address")
@Table(name = "addresses")
public class AddressEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String street;
    @Column
    private String houseNumber;
    @Column
    private String city;
    @Column
    private String zip;
    @Column
    private String country;

    public AddressEntity() {
    }

    public AddressEntity(Builder builder) {
        this.id = builder.id;
        this.street = builder.street;
        this.houseNumber = builder.houseNumber;
        this.city = builder.city;
        this.zip = builder.zip;
        this.country = builder.country;
    }

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public String getCountry() {
        return country;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String street;
        private String houseNumber;
        private String city;
        private String zip;
        private String country;

        public Builder id(Long id) {
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

        public AddressEntity build() {
            return new AddressEntity(this);
        }
    }
}
