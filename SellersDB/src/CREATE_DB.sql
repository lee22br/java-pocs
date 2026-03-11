-- 1. Create Customers table
CREATE TABLE customers (
    customer_id SERIAL PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    tax_id VARCHAR(20) UNIQUE NOT NULL, -- CPF/CNPJ
    email VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Create Sellers table
CREATE TABLE sellers (
    seller_id SERIAL PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    employee_code VARCHAR(20) UNIQUE NOT NULL,
    commission_rate DECIMAL(5,2) DEFAULT 0.00
);

-- 3. Create Products table
CREATE TABLE products (
    product_id SERIAL PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    stock_quantity INT DEFAULT 0
);

-- 4. Create Sales table (Header)
CREATE TABLE sales (
    sale_id SERIAL PRIMARY KEY,
    customer_id INT REFERENCES customers(customer_id),
    seller_id INT REFERENCES sellers(seller_id),
    sale_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    state_code CHAR(2) NOT NULL,
    total_amount DECIMAL(10,2) DEFAULT 0.00
);

-- 5. Create Sale Items table (Details/Line Items)
CREATE TABLE sale_items (
    item_id SERIAL PRIMARY KEY,
    sale_id INT REFERENCES sales(sale_id) ON DELETE CASCADE,
    product_id INT REFERENCES products(product_id),
    quantity INT NOT NULL,
    historical_unit_price DECIMAL(10,2) NOT NULL
);

--Query's
--Melhor Vendedor por Estado
SELECT state_code, seller_name, total_sold
FROM (
    SELECT
        s.state_code,
        sel.full_name AS seller_name,
        SUM(s.total_amount) AS total_sold,
        RANK() OVER (PARTITION BY s.state_code ORDER BY SUM(s.total_amount) DESC) as ranking
    FROM sales s
    JOIN sellers sel ON s.seller_id = sel.seller_id
    GROUP BY s.state_code, sel.full_name
) ranked_sales
WHERE ranking = 1;
--Melhor Vendedor do Ano
SELECT
    sel.full_name AS seller_name,
    SUM(s.total_amount) AS total_yearly_revenue
FROM sales s
JOIN sellers sel ON s.seller_id = sel.seller_id
WHERE EXTRACT(YEAR FROM s.sale_date) = 2023 -- Defina o ano aqui
GROUP BY sel.full_name
ORDER BY total_yearly_revenue DESC
LIMIT 1;


--Populate Tables
-- 1. Populating Catalog Tables (Base Data)
INSERT INTO customers (full_name, tax_id, email)
SELECT
    'Customer ' || i,
    'TAX' || LPAD(i::text, 7, '0'),
    'customer' || i || '@example.com'
FROM generate_series(1, 20) s(i);

INSERT INTO sellers (full_name, employee_code, commission_rate)
VALUES
    ('Alice Johnson', 'EMP001', 5.0),
    ('Bob Smith', 'EMP002', 4.5),
    ('Charlie Davis', 'EMP003', 5.5),
    ('Diana Prince', 'EMP004', 5.0);

INSERT INTO products (product_name, unit_price, stock_quantity)
VALUES
    ('Laptop Pro', 1200.00, 50),
    ('Smartphone X', 800.00, 100),
    ('Wireless Mouse', 25.00, 200),
    ('Mechanical Keyboard', 150.00, 80),
    ('Monitor 27-inch', 300.00, 40);

-- 2. Generating 150 Random Sales
-- We use random() to pick IDs and States
INSERT INTO sales (customer_id, seller_id, sale_date, state_code, total_amount)
SELECT
    floor(random() * 20 + 1)::int,         -- Random customer (1-20)
    floor(random() * 4 + 1)::int,          -- Random seller (1-4)
    NOW() - (random() * interval '365 days'), -- Random date within the last year
    (ARRAY['NY', 'CA', 'TX', 'FL', 'WA'])[floor(random() * 5 + 1)], -- Random State
    0 -- Initial total, will be updated based on items
FROM generate_series(1, 150) s(i);

-- 3. Generating Sale Items (Details)
-- This creates 1 to 3 items for each of the 150 sales
INSERT INTO sale_items (sale_id, product_id, quantity, historical_unit_price)
SELECT
    s.sale_id,
    p.product_id,
    floor(random() * 3 + 1)::int, -- Random quantity (1-3)
    p.unit_price
FROM sales s
CROSS JOIN LATERAL (
    SELECT product_id, unit_price
    FROM products
    ORDER BY random()
    LIMIT floor(random() * 3 + 1)::int -- Each sale gets 1 to 3 random products
) p;

-- 4. Final Touch: Update Sales Total Amount
-- Synchronizes the 'sales' table with the sum of 'sale_items'
UPDATE sales s
SET total_amount = (
    SELECT SUM(si.quantity * si.historical_unit_price)
    FROM sale_items si
    WHERE si.sale_id = s.sale_id
);

-- 1. View para Ranking de Vendedores por Estado
-- Esta visão já traz o total vendido e o ranking dentro de cada estado
CREATE OR REPLACE VIEW view_seller_performance_by_state AS
WITH state_ranking AS (
    SELECT
        s.state_code,
        sel.full_name AS seller_name,
        SUM(s.total_amount) AS total_sold,
        RANK() OVER (PARTITION BY s.state_code ORDER BY SUM(s.total_amount) DESC) as ranking
    FROM sales s
    JOIN sellers sel ON s.seller_id = sel.seller_id
    GROUP BY s.state_code, sel.full_name
)
SELECT state_code, seller_name, total_sold
FROM state_ranking
WHERE ranking = 1;

--Index
-- Melhora a velocidade de buscas por data (filtros de ano)
CREATE INDEX idx_sales_date ON sales(sale_date);

-- Melhora a velocidade de relatórios por estado
CREATE INDEX idx_sales_state ON sales(state_code);

-- 2. View para Performance Anual dos Vendedores
-- Facilita a busca pelo melhor do ano filtrando apenas o ano desejado
CREATE OR REPLACE VIEW view_annual_seller_ranking AS
SELECT
    EXTRACT(YEAR FROM s.sale_date) AS sale_year,
    sel.full_name AS seller_name,
    SUM(s.total_amount) AS total_revenue
FROM sales s
JOIN sellers sel ON s.seller_id = sel.seller_id
GROUP BY sale_year, sel.full_name;



--Querys in view
SELECT * FROM view_seller_performance_by_state;

SELECT seller_name, total_revenue
FROM view_annual_seller_ranking
WHERE sale_year = 2025
ORDER BY total_revenue DESC
LIMIT 1;

--Triggers
CREATE OR REPLACE FUNCTION update_stock_after_sale()
RETURNS TRIGGER AS $$
BEGIN
    -- Atualiza o estoque subtraindo a quantidade vendida
    UPDATE products
    SET stock_quantity = stock_quantity - NEW.quantity
    WHERE product_id = NEW.product_id;

    -- Opcional: Verificar se o estoque ficou negativo
    IF (SELECT stock_quantity FROM products WHERE product_id = NEW.product_id) < 0 THEN
        RAISE EXCEPTION 'Insufficient stock for product ID %', NEW.product_id;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_update_stock_on_sale
AFTER INSERT ON sale_items
FOR EACH ROW
EXECUTE FUNCTION update_stock_after_sale();


--Trigger TEST
-- Supondo que o produto 1 tenha 50 unidades
INSERT INTO sale_items (sale_id, product_id, quantity, historical_unit_price)
VALUES (1, 1, 5, 1200.00);

-- Verifique o estoque: ele deve estar em 45 agora
SELECT product_name, stock_quantity FROM products WHERE product_id = 1;