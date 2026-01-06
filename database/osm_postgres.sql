CREATE TABLE customer (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(100) UNIQUE
);

-- Order table
CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    order_code VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(10) NOT NULL CHECK (status IN ('NEW', 'PAID', 'CANCEL')),
    total_amount NUMERIC(12,2) NOT NULL DEFAULT 0,
    customer_id INT NOT NULL,
    CONSTRAINT fk_order_customer
        FOREIGN KEY (customer_id)
        REFERENCES customer(id)
        ON DELETE CASCADE
);

-- OrderItem table
CREATE TABLE order_item (
    id SERIAL PRIMARY KEY,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    price NUMERIC(12,2) NOT NULL CHECK (price >= 0),
    CONSTRAINT fk_order_item_order
        FOREIGN KEY (order_id)
        REFERENCES orders(id)
        ON DELETE CASCADE
);


INSERT INTO customer (name, phone, email) VALUES
('Nguyen Van A', '0901234567', 'nguyenvana@gmail.com'),
('Tran Thi B', '0912345678', 'tranthib@gmail.com'),
('Le Van C', '0923456789', 'levanc@gmail.com');

INSERT INTO orders (order_code, status, total_amount, customer_id) VALUES
('ORD001', 'NEW', 0, 1),
('ORD002', 'PAID', 0, 2),
('ORD003', 'CANCEL', 0, 1);

INSERT INTO order_item (order_id, product_id, quantity, price) VALUES
(1, 101, 2, 150000),
(1, 102, 1, 300000);

INSERT INTO order_item (order_id, product_id, quantity, price) VALUES
(2, 103, 3, 200000),
(2, 104, 1, 500000);

INSERT INTO order_item (order_id, product_id, quantity, price) VALUES
(3, 105, 1, 100000);
