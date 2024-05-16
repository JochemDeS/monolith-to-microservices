package com.example.monolithtomicroservices.data;

import com.example.monolithtomicroservices.domain.Brand;
import com.example.monolithtomicroservices.domain.BrandId;

public enum BrandDataFactory {
    APPLE(Brand.builder()
            .id(BrandId.builder()
                    .id(1L)
                    .build())
            .name("Apple").build()),
    SAMSUNG(Brand.builder()
            .id(BrandId.builder()
                    .id(2L)
                    .build())
            .name("Samsung").build()),
    OREAL_PARIS(Brand.builder()
            .id(BrandId.builder()
                    .id(3L)
                    .build())
            .name("Oreal Paris").build());

    private final Brand brand;

    BrandDataFactory(Brand brand) {
        this.brand = brand;
    }

    public Brand getBrand() {
        return brand;
    }
}
