CREATE TABLE IF NOT EXISTS cart_items(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    customer_id BIGSERIAL NOT NULL,
    product_id BIGSERIAL NOT NULL,
    quantity INTEGER,
    CONSTRAINT fk_customer_id FOREIGN KEY (customer_id) REFERENCES customers(id),
    CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES products(id)
);
