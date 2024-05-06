package com.example.monolithtomicroservices.domain;

import java.util.List;

public record ShoppingCart(ShoppingCartId id, Customer customer, List<ShoppingCartItem> items) {
    private ShoppingCart(Builder builder) {
        this(builder.id, builder.customer, builder.items);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ShoppingCartId id;
        private Customer customer;
        private List<ShoppingCartItem> items;

        public Builder id(ShoppingCartId id) {
            this.id = id;
            return this;
        }

        public Builder customer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Builder items(List<ShoppingCartItem> items) {
            this.items = items;
            return this;
        }

        public ShoppingCart build() {
            return new ShoppingCart(this);
        }
    }
}
