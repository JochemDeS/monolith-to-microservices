package com.example.monolithtomicroservices.domain;

public record User(
        UserId id,
        String username,
        String password,
        String email,
        Name name,
        Cart cart
) {
    public User(final Builder builder) {
        this(builder.id, builder.username, builder.password, builder.email, builder.name, builder.cart);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UserId id;
        private String username;
        private String password;
        private String email;
        private Name name;
        private Cart cart = Cart.builder().build();

        public Builder id(UserId id) {
            this.id = id;
            return this;
        }

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

        public Builder cart(Cart cart) {
            this.cart = cart;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
