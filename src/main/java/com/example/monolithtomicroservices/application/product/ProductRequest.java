package com.example.monolithtomicroservices.application.product;

import com.example.monolithtomicroservices.domain.PriceRange;
import com.example.monolithtomicroservices.domain.RatingRange;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public record ProductRequest(
        String category,
        String brand,
        PriceRange priceRange,
        RatingRange ratingRange,
        Pageable pageable
) {
    private ProductRequest(final Builder builder) {
        this(builder.category, builder.brand, builder.priceRange, builder.ratingRange, builder.pageable);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String category;
        private String brand;
        private PriceRange priceRange;
        private RatingRange ratingRange;
        private Pageable pageable;

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder priceRange(PriceRange priceRange) {
            this.priceRange = priceRange;
            return this;
        }

        public Builder ratingRange(RatingRange ratingRange) {
            this.ratingRange = ratingRange;
            return this;
        }

        public Builder pageable(Pageable pageable) {
            this.pageable = pageable;
            return this;
        }

        public ProductRequest build() {
            return new ProductRequest(this);
        }
    }
}
