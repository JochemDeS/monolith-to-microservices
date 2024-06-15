package com.example.monolithtomicroservices.domain;

public record CartItem(CartItemId id, Product product, int quantity) {
    private CartItem(Builder builder) {
        this(builder.id, builder.product, builder.quantity);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private CartItemId id;
        private Product product;
        private int quantity;

        public Builder id(CartItemId id) {
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

        public CartItem build() {
            return new CartItem(this);
        }
    }
}
