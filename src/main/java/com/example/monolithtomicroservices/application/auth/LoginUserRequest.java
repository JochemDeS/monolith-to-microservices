package com.example.monolithtomicroservices.application.auth;

public record LoginUserRequest(String username, String password) {
    private LoginUserRequest(Builder builder) {
        this(builder.username, builder.password);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String username;
        private String password;

        public Builder username(final String username) {
            this.username = username;
            return this;
        }

        public Builder password(final String password) {
            this.password = password;
            return this;
        }

        public LoginUserRequest build() {
            return new LoginUserRequest(this);
        }
    }
}
