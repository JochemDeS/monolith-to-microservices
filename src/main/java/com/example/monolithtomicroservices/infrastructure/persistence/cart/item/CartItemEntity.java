package com.example.monolithtomicroservices.infrastructure.persistence.cart.item;

import com.example.monolithtomicroservices.infrastructure.persistence.cart.CartEntity;
import com.example.monolithtomicroservices.infrastructure.persistence.product.ProductEntity;
import jakarta.persistence.*;

@Entity(name = "CartItem")
@Table(name = "cart_items")
public class CartItemEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "cart_id", nullable = false)
    private CartEntity cart;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;
    @Column
    private int quantity;

    public CartItemEntity() {
    }

    private CartItemEntity(Builder builder) {
        this.id = builder.id;
        this.cart = builder.cart;
        this.product = builder.product;
        this.quantity = builder.quantity;
    }

    public Long getId() {
        return id;
    }

    public CartEntity getCart() {
        return cart;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private CartEntity cart;
        private ProductEntity product;
        private int quantity;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder cart(CartEntity cart) {
            this.cart = cart;
            return this;
        }

        public Builder product(ProductEntity product) {
            this.product = product;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public CartItemEntity build() {
            return new CartItemEntity(this);
        }
    }
}
