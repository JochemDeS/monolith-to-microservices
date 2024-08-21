package com.example.monolithtomicroservices.infrastructure.persistence.order;

import com.example.monolithtomicroservices.infrastructure.persistence.product.ProductEntity;
import jakarta.persistence.*;

@Entity(name = "OrderItem")
@Table(name = "order_items")
public class OrderItemEntity {
    @Id @ManyToOne @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;
    @Id @OneToOne @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;
    @Column
    private int quantity;

    public OrderItemEntity() {
    }

    private OrderItemEntity(Builder builder) {
        this.order = builder.order;
        this.product = builder.product;
        this.quantity = builder.quantity;
    }

    public OrderEntity getOrder() {
        return order;
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
        private OrderEntity order;
        private ProductEntity product;
        private int quantity;

        public Builder order(OrderEntity order) {
            this.order = order;
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

        public OrderItemEntity build() {
            return new OrderItemEntity(this);
        }
    }
}
