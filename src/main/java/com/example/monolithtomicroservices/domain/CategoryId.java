package com.example.monolithtomicroservices.domain;

public record CategoryId(long id) {
    private CategoryId(Builder builder) {
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

        public CategoryId build() {
            return new CategoryId(this.id);
        }
    }
}
