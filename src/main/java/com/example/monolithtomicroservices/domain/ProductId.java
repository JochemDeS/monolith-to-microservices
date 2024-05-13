package com.example.monolithtomicroservices.domain;

public record ProductId(long id) {
    private ProductId(Builder builder) {
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

        public Builder id(String id) {
            this.id = Long.parseLong(id);
            return this;
        }

        public ProductId build() {
            return new ProductId(this.id);
        }
    }
}
