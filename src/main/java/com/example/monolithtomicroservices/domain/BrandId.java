package com.example.monolithtomicroservices.domain;

public record BrandId(long id) {
    private BrandId(Builder builder) {
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

        public BrandId build() {
            return new BrandId(this.id);
        }
    }
}
