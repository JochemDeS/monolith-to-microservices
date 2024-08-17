package com.example.monolithtomicroservices.application.auth;

import com.example.monolithtomicroservices.domain.Name;

public record RegisterUserRequest(String username, String password, String email, Name name) {
    public RegisterUserRequest(Builder builder) {
        this(builder.username, builder.password, builder.email, builder.name);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String username;
        private String password;
        private String email;
        private Name name;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder name(Name name) {
            this.name = name;
            return this;
        }

        public RegisterUserRequest build() {
            return new RegisterUserRequest(this);
        }
    }
}
