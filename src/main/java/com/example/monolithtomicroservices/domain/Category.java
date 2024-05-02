package com.example.monolithtomicroservices.domain;

public record Category(CategoryId id, String name) {
    private Category(Builder builder) {
        this(builder.id, builder.name);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private CategoryId id;
        private String name;

        public Builder id(CategoryId id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Category build() {
            return new Category(this);
        }
    }
}
