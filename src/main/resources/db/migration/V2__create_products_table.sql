CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    discountPercentage DECIMAL(10, 2) NOT NULL,
    rating DECIMAL(10, 2) NOT NULL,
    stock INTEGER NOT NULL,
    brand BIGSERIAL NOT NULL,
    category BIGSERIAL NOT NULL,
    thumbnail TEXT NOT NULL,
    image_url TEXT NOT NULL
);