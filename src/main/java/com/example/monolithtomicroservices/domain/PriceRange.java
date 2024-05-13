package com.example.monolithtomicroservices.domain;

public record PriceRange(double min, double max) {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private double min;
        private double max;

        public Builder min(double min) {
            this.min = min;
            return this;
        }

        public Builder max(double max) {
            this.max = max;
            return this;
        }

        public PriceRange build() {
            return new PriceRange(min, max);
        }
    }
}
