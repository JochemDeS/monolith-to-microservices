CREATE TABLE IF NOT EXISTS addresses(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    street VARCHAR(255) NOT NULL,
    house_number VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    zip VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    CONSTRAINT unique_address UNIQUE (street, house_number, city, zip, country)
)