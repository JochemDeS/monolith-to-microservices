CREATE TABLE IF NOT EXISTS customers(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    address_id BIGSERIAL,
    email VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT current_timestamp,
    CONSTRAINT fk_address_id FOREIGN KEY (address_id) REFERENCES addresses(id)
);
