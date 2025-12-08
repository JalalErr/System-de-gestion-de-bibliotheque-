CREATE DATABASE IF NOT EXISTS mydb_2;
USE mydb_2;

CREATE TABLE users
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    username   VARCHAR(50)  NOT NULL UNIQUE,
    email      VARCHAR(100) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    full_name  VARCHAR(100) NOT NULL,
    role       ENUM('ADMIN', 'USER', 'MANAGER') DEFAULT 'USER',
    is_active  BOOLEAN   DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX      idx_username (username),
    INDEX      idx_email (email),
    INDEX      idx_role (role)
);

-- Sample data
INSERT INTO users (username, email, password, full_name, role, is_active)
VALUES ('admin', 'admin@company.com', 'admin123', 'System Administrator', 'ADMIN', TRUE),
       ('john_doe', 'john@company.com', 'pass123', 'John Doe', 'USER', TRUE),
       ('jane_smith', 'jane@company.com', 'pass123', 'Jane Smith', 'MANAGER', TRUE),
       ('bob_wilson', 'bob@company.com', 'pass123', 'Bob Wilson', 'USER', FALSE);