package com.example.monolithtomicroservices.domain;

public record AddressId(long id) {

    private AddressId(Builder builder) {
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

        public AddressId build() {
            return new AddressId(this);
        }
    }
}
