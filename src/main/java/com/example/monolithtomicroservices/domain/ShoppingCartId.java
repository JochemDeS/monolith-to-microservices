package com.example.monolithtomicroservices.domain;

public record ShoppingCartId(long id) {
    private ShoppingCartId(Builder builder) {
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

        public ShoppingCartId build() {
            return new ShoppingCartId(this);
        }
    }
}
