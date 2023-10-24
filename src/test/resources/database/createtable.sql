CREATE TABLE IF NOT EXISTS managers
(
    id             UUID PRIMARY KEY,
    first_name     VARCHAR(25),
    last_name      VARCHAR(25),
    status         VARCHAR(20),
    created_at     TIMESTAMP,
    updated_at     TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS clients
(
    id             UUID PRIMARY KEY,
    manager_id     UUID,
    status         VARCHAR(20),
    tax_code       VARCHAR(25),
    first_name     VARCHAR(25),
    last_name      VARCHAR(25),
    email          VARCHAR(25),
    address        VARCHAR(255),
    phone          VARCHAR(25),
    created_at     TIMESTAMP,
    updated_at     TIMESTAMP,
    FOREIGN KEY (manager_id) REFERENCES managers (id)
    );

CREATE TABLE IF NOT EXISTS accounts
(
    id             UUID PRIMARY KEY,
    client_id      UUID,
    status         VARCHAR(20),
    currency_code  VARCHAR(3),
    type           VARCHAR(20),
    balance        DECIMAL(15, 2),
    name           VARCHAR(25),
    created_at     TIMESTAMP,
    updated_at     TIMESTAMP,
    FOREIGN KEY (client_id) REFERENCES clients (id)
    );

CREATE TABLE IF NOT EXISTS products
(
    id                        UUID PRIMARY KEY,
    manager_id                UUID,
    name                      VARCHAR(25),
    status                    VARCHAR(20),
    currency_code             VARCHAR(3),
    interest_rate             DECIMAL(6, 4),
    min_limit                 INT,
    created_at                TIMESTAMP,
    updated_at                TIMESTAMP,
    FOREIGN KEY (manager_id) REFERENCES managers (id)
    );

CREATE TABLE IF NOT EXISTS agreements
(
    id             UUID PRIMARY KEY,
    account_id     UUID,
    product_id     UUID,
    status         VARCHAR(20),
    created_at     TIMESTAMP,
    updated_at     TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES accounts (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
    );

CREATE TABLE IF NOT EXISTS transactions
(
    id                        UUID PRIMARY KEY,
    debit_account_id          UUID,
    credit_account_id         UUID,
    status                    VARCHAR(20),
    amount                    DECIMAL(12, 4),
    description               VARCHAR(255),
    created_at                TIMESTAMP,
    updated_at                TIMESTAMP,
    FOREIGN KEY (debit_account_id) REFERENCES accounts (id),
    FOREIGN KEY (credit_account_id) REFERENCES accounts (id)
    );