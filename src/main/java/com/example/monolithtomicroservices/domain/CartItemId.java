package com.example.monolithtomicroservices.domain;

public record CartItemId(long id) {
    private CartItemId(Builder builder) {
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

        public CartItemId build() {
            return new CartItemId(this);
        }
    }
}
