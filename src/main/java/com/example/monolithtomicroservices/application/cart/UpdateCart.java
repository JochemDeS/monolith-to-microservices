package com.example.monolithtomicroservices.application.cart;

import com.example.monolithtomicroservices.domain.ProductId;
import com.example.monolithtomicroservices.domain.User;

public record UpdateCart(User user, ProductId productId, int quantity) {
    private UpdateCart(Builder builder) {
        this(builder.user, builder.productId, builder.quantity);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private User user;
        private ProductId productId;
        private int quantity;

        public Builder user(User user) {
            this.user = user;
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

        public UpdateCart build() {
            return new UpdateCart(this);
        }
    }
}
