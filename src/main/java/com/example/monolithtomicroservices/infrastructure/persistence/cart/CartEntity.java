package com.example.monolithtomicroservices.infrastructure.persistence.cart;

import com.example.monolithtomicroservices.infrastructure.persistence.cart.item.CartItemEntity;
import com.example.monolithtomicroservices.infrastructure.persistence.user.UserEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Cart")
@Table(name = "carts")
public class CartEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    @Column
    private LocalDateTime createdAt;
    @Column
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<CartItemEntity> items = new ArrayList<>();

    public CartEntity() {
    }

    private CartEntity(final Builder builder) {
        this.id = builder.id;
        this.user = builder.user;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public long getId() {
        return id;
    }

    public UserEntity getUser() {
        return user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<CartItemEntity> getItems() {
        return items;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private long id;
        private UserEntity user;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder user(UserEntity user) {
            this.user = user;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public CartEntity build() {
            return new CartEntity(this);
        }
    }
}
