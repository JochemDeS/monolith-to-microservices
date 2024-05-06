package com.example.monolithtomicroservices.domain;

public record ShoppingCartItem(ShoppingCartItemId id, Product product, int quantity) {
    private ShoppingCartItem(Builder builder) {
        this(builder.id, builder.product, builder.quantity);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ShoppingCartItemId id;
        private Product product;
        private int quantity;

        public Builder id(ShoppingCartItemId id) {
            this.id = id;
            return this;
        }

        public Builder product(Product product) {
            this.product = product;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public ShoppingCartItem build() {
            return new ShoppingCartItem(this);
        }
    }
}
