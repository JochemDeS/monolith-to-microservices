package com.example.monolithtomicroservices.domain;

public record Product(ProductId id, String title, String description, double price, double discountPercentage,
                      double rating, int stock, Brand brand, Category category, String thumbnail, String image
) {
}
