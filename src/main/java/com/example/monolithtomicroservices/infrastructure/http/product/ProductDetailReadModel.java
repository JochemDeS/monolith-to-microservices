package com.example.monolithtomicroservices.infrastructure.http.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.URL;

public record ProductDetailReadModel(@Positive @Schema(description = "Product value", defaultValue = "4") long id,
                                     @NotBlank @Schema(description = "Name of product", defaultValue = "Iphone X") String title,
                                     @NotBlank @Schema(description = "Description of the product", defaultValue = "Made by Apple") String description,
                                     @Positive @Schema(description = "Price of the product", defaultValue = "1250.99") double price,
                                     @Positive @Schema(description = "Number of units in stock", defaultValue = "1234") int stock,
                                     @NotBlank @Schema(description = "Brand of the product", defaultValue = "Apple") String brand,
                                     @NotBlank @Schema(description = "Category of the product", defaultValue = "Smartphone") String category,
                                     @URL @Schema(description = "URL of thumbnail image", defaultValue = "https://cdn.dummyjson.com/product-images/2/thumbnail.jpg") String thumbnail,
                                     @URL @Schema(description = "URL of product image", defaultValue = "https://cdn.dummyjson.com/product-images/2/1.jpg") String image) {
    private ProductDetailReadModel(Builder builder) {
        this(builder.id, builder.title, builder.description, builder.price, builder.stock, builder.brand,
                builder.category, builder.thumbnail, builder.image);
    }

    public static Builder builder() {
        return new Builder();
    }

public static class Builder {
        private long id;
        private String title;
        private String description;
        private double price;
        private int stock;
        private String brand;
        private String category;
        private String thumbnail;
        private String image;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Builder stock(int stock) {
            this.stock = stock;
            return this;
        }

        public Builder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder thumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        public Builder image(String image) {
            this.image = image;
            return this;
        }

        public ProductDetailReadModel build() {
            return new ProductDetailReadModel(this);
        }
    }
}
