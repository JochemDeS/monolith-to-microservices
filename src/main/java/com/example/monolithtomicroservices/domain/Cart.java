package com.example.monolithtomicroservices.domain;

import java.util.List;

public record Cart(List<CartItem> items) {
    private Cart(Builder builder) {
        this(builder.items);
    }

    public void add(CartItem item) {
        items.add(item);
    }

    public void remove(CartItemId itemId) {
        items.stream().filter(item -> item.id().equals(itemId)).findFirst().ifPresent(items::remove);
    }

    public void update(CartItem item) {
        remove(item.id());
        add(item);
    }

    public void reset() {
        items.clear();
    }

    public double getTotalPrice() {
        return items.stream().mapToDouble(item -> item.product().price() * item.quantity()).sum();
    }

    public int getTotalNumberOfItems() {
        return items.stream().mapToInt(CartItem::quantity).sum();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<CartItem> items;

        public Builder items(List<CartItem> items) {
            this.items = items;
            return this;
        }

        public Cart build() {
            return new Cart(this);
        }
    }
}
