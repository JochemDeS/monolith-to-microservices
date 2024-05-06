package com.example.monolithtomicroservices.domain;

public record ShoppingCartItemId(long id) {
    private ShoppingCartItemId(Builder builder) {
        this(builder.id);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private long id;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public ShoppingCartItemId build() {
            return new ShoppingCartItemId(this);
        }
    }
}
