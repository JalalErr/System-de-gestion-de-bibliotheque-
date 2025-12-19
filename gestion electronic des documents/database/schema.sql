CREATE DATABASE IF NOT EXISTS mysql;
USE mysql;

CREATE TABLE comptes
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    nom        VARCHAR(50)  NOT NULL UNIQUE,
    prenom     VARCHAR(50)  NOT NULL UNIQUE,
    email      VARCHAR(100) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    role       ENUM('ADMIN', 'USER') DEFAULT 'USER',
    INDEX      idx_username (nom),
    INDEX      idx_email (email),
    INDEX      idx_role (role)
);

-- Sample data
INSERT INTO comptes (nom, prenom, email, password, role)
VALUES ('admin', 'qdmin','admin@company.com', 'admin123', 'ADMIN'),
       ('john','doe' ,'john@company.com', 'pass123', 'USER'),
       ('jane', 'smith','jane@company.com', 'pass123', 'USER'),
       ('bob','Wilson', 'bob@company.com', 'pass123', 'USER');
