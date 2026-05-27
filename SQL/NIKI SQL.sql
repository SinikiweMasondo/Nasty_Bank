DROP DATABASE IF EXISTS banking_system; 
CREATE DATABASE banking_system; 
USE banking_system; 
 
CREATE TABLE branches (     branch_id INT PRIMARY KEY AUTO_INCREMENT,     branch_name VARCHAR(50) NOT NULL,     location VARCHAR(100) NOT NULL 
); 
 
CREATE TABLE customers (     customer_id INT PRIMARY KEY AUTO_INCREMENT,     first_name VARCHAR(50) NOT NULL,     last_name VARCHAR(50) NOT NULL,     email VARCHAR(100) UNIQUE NOT NULL,     phone VARCHAR(15) NOT NULL 
); 
 
CREATE TABLE accounts (     account_number INT PRIMARY KEY AUTO_INCREMENT,     customer_id INT NOT NULL,     account_type VARCHAR(20) NOT NULL DEFAULT 'Savings',     balance DECIMAL(15, 2) NOT NULL DEFAULT 0.00 CHECK (balance >= 0),     branch_id INT NOT NULL DEFAULT 1, 
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ON DELETE CASCADE,     FOREIGN KEY (branch_id) REFERENCES branches(branch_id) 
); 
 
CREATE TABLE transactions ( 
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,     account_number INT NOT NULL,     transaction_type VARCHAR(20) NOT NULL,     amount DECIMAL(15, 2) NOT NULL, 
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    FOREIGN KEY (account_number) REFERENCES accounts(account_number) ON DELETE CASCADE 
); 
 
CREATE TABLE employees (     employee_id INT PRIMARY KEY AUTO_INCREMENT,     first_name VARCHAR(50) NOT NULL,     last_name VARCHAR(50) NOT NULL,     branch_id INT NOT NULL, 
    FOREIGN KEY (branch_id) REFERENCES branches(branch_id) 
); 
 
INSERT INTO branches (branch_name, location) VALUES  
('Main Branch', 'Pretoria'), 
('East Loop Branch', 'Johannesburg'), 
('Coastal Hub', 'Durban'), 
('Table Mountain Suite', 'Cape Town'), 
('Savannah Outpost', 'Polokwane'); 
 
INSERT INTO customers (first_name, last_name, email, phone) VALUES  
('Luyanda', 'Njolo', 'Luyanda@email.com', '0123456789'), 
('Sipho', 'Khumalo', 'sipho@email.com', '0821112222'), 
('Amara', 'Okafor', 'amara@email.com', '0733334444'), 
('Zane', 'DuPlessis', 'zane@email.com', '0615556666'), 
('Thabo', 'Mokoena', 'thabo@email.com', '0847778888'); 
 
INSERT INTO accounts (customer_id, account_type, balance, branch_id) VALUES  
(1, 'Savings', 8500.00, 1), 
(2, 'Cheque', 12000.50, 2), 
(3, 'Savings', 350.00, 1), 
(4, 'Savings', 45000.00, 4), 
(5, 'Cheque', 150.00, 5); 
 
INSERT INTO transactions (account_number, transaction_type, amount) VALUES  
(1, 'Deposit', 8500.00), 
(2, 'Deposit', 12000.50), 
(3, 'Deposit', 350.00), 
(4, 'Deposit', 45000.00), 
(5, 'Deposit', 150.00); 
 
INSERT INTO employees (first_name, last_name, branch_id) VALUES  
('Kea', 'Yanda', 1), 
('Pieter', 'Botha', 2), 
('Fatima', 'Patel', 3), 
('Naledi', 'Zulu', 4), 
('Sbu', 'Nkosi', 5); 
 
SELECT * FROM accounts WHERE balance >= 10000.00; 
SELECT * FROM accounts WHERE branch_id = 1; 
SELECT * FROM employees WHERE last_name = 'Botha'; 
SELECT * FROM accounts WHERE balance < 500.00; 
SELECT * FROM customers WHERE email = 'Luyanda@email.com'; 
 
UPDATE accounts SET balance = balance + 500.00 WHERE account_number = 1; 
UPDATE branches SET branch_name = 'Pretoria Central Corporate Hub' WHERE branch_id = 1; 
 
DELETE FROM transactions WHERE transaction_id = 5; 
DELETE FROM employees WHERE employee_id = 5;