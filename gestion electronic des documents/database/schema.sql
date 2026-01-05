-- =========================================
-- 1️⃣ Table Admin
-- =========================================
CREATE TABLE admin (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       nom VARCHAR(100) NOT NULL,
                        pernom varchar(100),
                       email VARCHAR(100) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(20) DEFAULT 'ADMIN'
);
INSERT INTO admin (nom,pernom, email, password, role)
VALUES ('admin', 'qdmin','admin@company.com', 'admin123', 'ADMIN'),
       ('john','doe' ,'john@company.com', 'pass123', 'USER'),
       ('jane', 'smith','jane@company.com', 'pass123', 'USER'),
       ('bob','Wilson', 'bob@company.com', 'pass123', 'USER');

-- =========================================
-- 2️⃣ Table Adherent
-- =========================================
CREATE TABLE adherent (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          nom VARCHAR(100) NOT NULL,
                          prenom VARCHAR(100) NOT NULL,
                          email VARCHAR(100) UNIQUE NOT NULL,
                          created_by_admin_id INT NOT NULL,
                          FOREIGN KEY (created_by_admin_id) REFERENCES admin(id)
);

-- =========================================
-- 3️⃣ Table Livre
-- =========================================
CREATE TABLE livre (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       titre VARCHAR(200) NOT NULL,
                       auteur VARCHAR(150),
                       isbn VARCHAR(50),
                       stock INT DEFAULT 1
);

-- =========================================
-- 4️⃣ Table Rapport
-- =========================================
CREATE TABLE rapport (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         titre VARCHAR(200) NOT NULL,
                         theme VARCHAR(150),
                         annee INT
);

-- =========================================
-- 5️⃣ Table Emprunt
-- =========================================
CREATE TABLE emprunt (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         adherent_id INT NOT NULL,
                         document_type ENUM('LIVRE','RAPPORT') NOT NULL,
                         document_id INT NOT NULL,
                         date_emprunt DATE NOT NULL,
                         date_retour_prevue DATE NOT NULL,
                         date_retour_effective DATE,
                         FOREIGN KEY (adherent_id) REFERENCES adherent(id)
);


-- =========================================
-- 6️⃣ Table Sanction
-- =========================================
CREATE TABLE sanction (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          emprunt_id INT NOT NULL,
                          raison VARCHAR(255) NOT NULL,
                          date_sanction DATETIME DEFAULT CURRENT_TIMESTAMP,
                          active BOOLEAN DEFAULT TRUE,
                          FOREIGN KEY (emprunt_id) REFERENCES emprunt(id)
);

-- =========================================
-- 7️⃣ Table HistoriqueAdmin
-- =========================================
CREATE TABLE historique_admin (
                                  id INT AUTO_INCREMENT PRIMARY KEY,
                                  admin_id INT NOT NULL,
                                  action VARCHAR(100) NOT NULL,
                                  description TEXT,
                                  date_action DATETIME DEFAULT CURRENT_TIMESTAMP,
                                  FOREIGN KEY (admin_id) REFERENCES admin(id)
);

