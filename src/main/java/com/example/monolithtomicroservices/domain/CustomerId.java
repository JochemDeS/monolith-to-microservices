package com.example.monolithtomicroservices.domain;

public record CustomerId(long id) {

    private CustomerId(Builder builder) {
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

        public CustomerId build() {
            return new CustomerId(this);
        }
    }
}
