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
    private String zipCode;
    @Column
    private String country;

    public AddressEntity() {
    }

    public AddressEntity(Long id, String street, String houseNumber, String city, String zipCode, String country) {
        this.id = id;
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
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

    public String getZipCode() {
        return zipCode;
    }

    public String getCountry() {
        return country;
    }
}
