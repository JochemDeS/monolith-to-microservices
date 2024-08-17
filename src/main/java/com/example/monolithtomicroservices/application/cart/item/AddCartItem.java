package com.example.monolithtomicroservices.application.cart.item;

import com.example.monolithtomicroservices.domain.ProductId;
import com.example.monolithtomicroservices.domain.UserId;

public record AddCartItem(UserId userId, ProductId productId, int quantity) {
    private AddCartItem(Builder builder) {
        this(builder.userId, builder.productId, builder.quantity);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UserId userId;
        private ProductId productId;
        private int quantity;

        public Builder userId(UserId userId) {
            this.userId = userId;
            return this;
        }

        public Builder productId(ProductId productId) {
            this.productId = productId;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public AddCartItem build() {
            return new AddCartItem(this);
        }
    }
}
