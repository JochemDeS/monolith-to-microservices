package com.example.monolithtomicroservices.infrastructure.persistence.user;

import com.example.monolithtomicroservices.infrastructure.persistence.cart.CartEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity(name = "User")
@Table(name = "users")
public class UserEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true) @NotNull
    private String username;
    @Column @NotNull
    private String password;
    @Column(unique = true) @NotNull
    private String email;
    @Column @NotNull
    private String firstName;
    @Column @NotNull
    private String lastName;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private CartEntity cart;

    public UserEntity() {
    }

    public UserEntity(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.password = builder.password;
        this.email = builder.email;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.cart = builder.cart;
    }

    public Long id() {
        return id;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }

    public String email() {
        return email;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public CartEntity cart() {
        return cart;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String username;
        private String password;
        private String email;
        private String firstName;
        private String lastName;
        private CartEntity cart;

        public Builder id(Long id) {
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

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder cart(CartEntity cart) {
            this.cart = cart;
            return this;
        }

        public UserEntity build() {
            return new UserEntity(this);
        }
    }
}
