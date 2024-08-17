package com.example.monolithtomicroservices.data;

import com.example.monolithtomicroservices.domain.Product;
import com.example.monolithtomicroservices.domain.ProductId;

import java.util.List;

public class ProductDataFactory {
    public static List<Product> createProducts() {
        return List.of(
                Product.builder()
                        .id(ProductId.builder()
                                .value(1)
                                .build())
                        .title("Iphone 9")
                        .description("The best phone ever")
                        .price(1000.0)
                        .brand(BrandDataFactory.APPLE.getBrand())
                        .category(CategoryDataFactory.PERFUME.getCategory())
                        .stock(100)
                        .thumbnail("thumbnail")
                        .image("image")
                        .build(),
                Product.builder()
                        .id(ProductId.builder()
                                .value(2)
                                .build())
                        .title("Samsung Galaxy S10")
                        .description("The best phone ever")
                        .price(900.0)
                        .brand(BrandDataFactory.SAMSUNG.getBrand())
                        .category(CategoryDataFactory.SMARTPHONES.getCategory())
                        .stock(100)
                        .thumbnail("thumbnail")
                        .image("image")
                        .build(),
                Product.builder()
                        .id(ProductId.builder()
                                .value(3)
                                .build())
                        .title("Orial Paris")
                        .description("The best perfume ever")
                        .price(60.0)
                        .brand(BrandDataFactory.OREAL_PARIS.getBrand())
                        .category(CategoryDataFactory.PERFUME.getCategory())
                        .stock(100)
                        .thumbnail("thumbnail")
                        .image("image")
                        .build()
        );
    }
}
