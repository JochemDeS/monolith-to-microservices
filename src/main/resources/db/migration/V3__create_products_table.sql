CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    stock INTEGER NOT NULL,
    brand_id BIGSERIAL NOT NULL,
    category_id BIGSERIAL NOT NULL,
    thumbnail TEXT NOT NULL,
    image TEXT NOT NULL,
    CONSTRAINT fk_brand FOREIGN KEY(brand_id) REFERENCES brands(id),
    CONSTRAINT fk_category FOREIGN KEY(category_id) REFERENCES categories(id)
);