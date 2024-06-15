package com.example.monolithtomicroservices.domain;

public record CartId(long id) {
    private CartId(Builder builder) {
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

        public CartId build() {
            return new CartId(this);
        }
    }
}
