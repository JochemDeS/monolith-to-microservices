package com.example.monolithtomicroservices.application.order;

import com.example.monolithtomicroservices.domain.UserId;
import org.springframework.data.domain.Pageable;

public record OrderRequest(UserId userId, Pageable pageable) {
    private OrderRequest(Builder builder) {
        this(builder.userId, builder.pageable);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UserId userId;
        private Pageable pageable;

        public Builder userId(UserId userId) {
            this.userId = userId;
            return this;
        }

        public Builder pageable(Pageable pageable) {
            this.pageable = pageable;
            return this;
        }

        public OrderRequest build() {
            return new OrderRequest(this);
        }
    }
}
