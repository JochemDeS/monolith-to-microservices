CREATE TABLE IF NOT EXISTS user_addresses(
    user_id BIGSERIAL NOT NULL,
    address_id BIGSERIAL NOT NULL,
    PRIMARY KEY(user_id, address_id),
    FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY(address_id) REFERENCES addresses(id) ON DELETE CASCADE
);