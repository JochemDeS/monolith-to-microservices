package com.example.monolithtomicroservices.domain;

public record ProductId(String id) {
    private ProductId(Builder builder) {
        this(builder.id);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;

        public Builder id(long id) {
            this.id = String.valueOf(id);
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public ProductId build() {
            return new ProductId(this.id);
        }
    }
}
