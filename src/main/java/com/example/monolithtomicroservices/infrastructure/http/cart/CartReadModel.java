package com.example.monolithtomicroservices.infrastructure.http.cart;

import java.util.List;

public record CartReadModel(
        List<CartItemReadModel> items,
        double totalPrice
) {
    private CartReadModel(Builder builder) {
        this(builder.items, builder.totalPrice);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<CartItemReadModel> items;
        private double totalPrice;

        public Builder items(List<CartItemReadModel> items) {
            this.items = items;
            return this;
        }

        public Builder totalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public CartReadModel build() {
            return new CartReadModel(this);
        }
    }
}
