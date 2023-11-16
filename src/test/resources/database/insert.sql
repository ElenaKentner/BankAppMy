INSERT INTO managers (id, first_name, last_name, status, created_at, updated_at)
VALUES
    ('1370ec78-5c28-46c3-b8dd-8ebee695daea', 'Vasilev', 'Ivan', 'NEW', '2023-09-04 12:00:00', '2023-09-04 12:00:00');

INSERT INTO clients (id, manager_id, status, tax_code, first_name, last_name, email, address, phone, password, created_at, updated_at)
VALUES
    ('c48a263c-5a20-413e-8c9c-d89d83b1ee41', '1370ec78-5c28-46c3-b8dd-8ebee695daea', 'NEW', '12345678', 'Vasily', 'Ivanov', 'ivan@gmail.com', 'Krakow, Kalina, 15', '4867538990', '$2a$05$y6XduIfPLW0pvLsjJaAFxej3UbHAZneEPQiaV6ipO5aDWyA9UTxle', '2023-09-04 12:00:00', '2023-09-04 12:00:00'),
    ('fee1a328-e29b-4541-9284-b7c679e8a58e', '1370ec78-5c28-46c3-b8dd-8ebee695daea', 'NEW', '98765432', 'Vasja', 'Petrov', 'petrov@gmail.com', 'Warshaw, Kalina, 11', '6789908643', '$2a$05$y6XduIfPLW0pvLsjJaAFxej3UbHAZneEPQiaV6ipO5aDWyA9UTxle', '2023-10-04 12:00:00', '2023-10-04 12:00:00');

INSERT INTO accounts (id, client_id, status, currency_code, type, balance, name, created_at, updated_at)
VALUES
    ('e0a35315-4bb8-485a-a108-93b346ab452e','c48a263c-5a20-413e-8c9c-d89d83b1ee41', 'ACTIVE', 'USD', 'SAVINGS', '1000.00', '988776544332', '2023-09-05 12:00:00', '2023-09-05 12:00:00'),
    ('11f7986c-c1d8-4231-838a-e84b17ccebdb','c48a263c-5a20-413e-8c9c-d89d83b1ee41', 'ACTIVE', 'USD', 'SAVINGS', '1000.00', '657483958765', '2023-09-05 12:00:00', '2023-09-05 12:00:00');

INSERT INTO products (id, manager_id, name, status, currency_code, interest_rate, min_limit, created_at, updated_at)
VALUES
    ('afc9da7c-299d-4dd1-93bc-d71de8e5d1a6', '1370ec78-5c28-46c3-b8dd-8ebee695daea', 'name', 'ACTIVE', 'USD', '1.5', '100', '2023-10-05 12:00:00', '2023-10-05 12:00:00');

INSERT INTO agreements (id, account_id, product_id, status, created_at, updated_at)
VALUES
    ('f7f0763b-90cf-4a69-b015-4610094897a6', 'e0a35315-4bb8-485a-a108-93b346ab452e', 'afc9da7c-299d-4dd1-93bc-d71de8e5d1a6', 'NEW', '2023-10-05 13:00:00', '2023-10-05 13:00:00');

INSERT INTO transactions (id, debit_account_id, credit_account_id, status, amount, description, created_at, updated_at)
VALUES
    ('18fbdb44-5563-47c0-b42e-20d5405e187d','e0a35315-4bb8-485a-a108-93b346ab452e', '11f7986c-c1d8-4231-838a-e84b17ccebdb', 'ACTIVE', '2000.0', 'a new transaction was created', '2023-10-05 14:00:00', '2023-10-05 14:00:00');