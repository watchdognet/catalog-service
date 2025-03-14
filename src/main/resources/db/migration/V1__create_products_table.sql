CREATE TABLE IF NOT EXISTS products (
    id varchar PRIMARY KEY,
    product_name varchar(255) NOT NULL,
    description varchar NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT NULL
);
