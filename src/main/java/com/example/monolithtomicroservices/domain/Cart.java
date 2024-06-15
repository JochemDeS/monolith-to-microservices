package com.example.monolithtomicroservices.domain;

import java.util.List;

public record Cart(CartId id, List<CartItem> items) {
    private Cart(Builder builder) {
        this(builder.id, builder.items);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private CartId id;
        private List<CartItem> items;

        public Builder id(CartId id) {
            this.id = id;
            return this;
        }

        public Builder items(List<CartItem> items) {
            this.items = items;
            return this;
        }

        public Cart build() {
            return new Cart(this);
        }
    }
}
