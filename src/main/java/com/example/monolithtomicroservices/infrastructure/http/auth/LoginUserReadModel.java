package com.example.monolithtomicroservices.infrastructure.http.auth;

public record LoginUserReadModel(String token) {
    public LoginUserReadModel(final Builder builder) {
        this(builder.token);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String token;

        private Builder() {
        }

        public Builder token(final String token) {
            this.token = token;
            return this;
        }

        public LoginUserReadModel build() {
            return new LoginUserReadModel(this);
        }
    }
}
