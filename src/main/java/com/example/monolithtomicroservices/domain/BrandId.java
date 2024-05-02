package com.example.monolithtomicroservices.domain;

public record BrandId(String id) {
    private BrandId(Builder builder) {
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

        public BrandId build() {
            return new BrandId(this.id);
        }
    }
}
