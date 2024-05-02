package com.example.monolithtomicroservices.domain;

public record Brand(BrandId id, String name) {
    private Brand(Builder builder) {
        this(builder.id, builder.name);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private BrandId id;
        private String name;

        public Builder id(BrandId id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Brand build() {
            return new Brand(this);
        }
    }
}
