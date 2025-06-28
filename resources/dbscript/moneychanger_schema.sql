use moneychangerdb;

-- STEP 1: Create scheme table
CREATE TABLE scheme (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name_tag VARCHAR(100) NOT NULL,
                        description VARCHAR(500),
                        is_default BOOLEAN DEFAULT FALSE,
                        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        created_by INT,
                        updated_by INT NULL,
                        is_deleted TINYINT(1) DEFAULT 0
);

-- STEP 2: Create money_changer table
CREATE TABLE money_changer (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               company_name VARCHAR(255) NOT NULL,
                               email VARCHAR(255) NOT NULL UNIQUE,
                               date_of_incorporation DATE,
                               address TEXT,
                               country VARCHAR(100),
                               postal_code VARCHAR(20),
                               notes TEXT,
                               uen VARCHAR(100),
                               scheme_id INT,
                               created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               created_by INT,
                               updated_by INT NULL,
                               is_deleted TINYINT(1) DEFAULT 0
);

-- STEP 3: Create accounts table
CREATE TABLE accounts (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          money_changer_id INT NOT NULL,
                          role VARCHAR(100) NOT NULL,
                          email VARCHAR(255) NOT NULL UNIQUE,
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          created_by INT,
                          updated_by INT NULL,
                          is_deleted TINYINT(1) DEFAULT 0
);

-- STEP 4: Create currency_code table
CREATE TABLE currency_code (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               currency VARCHAR(10) NOT NULL,
                               description VARCHAR(255)
);

-- STEP 5: Create commission_rate table
CREATE TABLE commission_rate (
                                 id INT AUTO_INCREMENT PRIMARY KEY,
                                 currency_id INT NOT NULL,
                                 scheme_id   INT NOT NULL,
                                 rate DECIMAL(20,10) NOT NULL,
                                 created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                 created_by INT,
                                 updated_by INT NULL,
                                 is_deleted TINYINT(1) DEFAULT 0,
                                 CONSTRAINT fk_commission_rate_currency FOREIGN KEY (currency_id) REFERENCES currency_code(id),
                                 CONSTRAINT fk_commission_rate_scheme FOREIGN KEY (scheme_id) REFERENCES scheme(id),
                                 CONSTRAINT fk_commission_rate_created_by FOREIGN KEY (created_by) REFERENCES accounts(id),
                                 CONSTRAINT fk_commission_rate_updated_by FOREIGN KEY (updated_by) REFERENCES accounts(id)
);

-- STEP 6: Create company_commission_scheme table
CREATE TABLE company_commission_scheme (
                                           id INT AUTO_INCREMENT PRIMARY KEY,
                                           money_changer_id INT NOT NULL,
                                           scheme_id INT NOT NULL,
                                           created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                           updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                           created_by INT,
                                           updated_by INT NULL,
                                           is_deleted TINYINT(1) DEFAULT 0,
                                           CONSTRAINT fk_company_commission_money_changer FOREIGN KEY (money_changer_id) REFERENCES money_changer(id),
                                           CONSTRAINT fk_company_commission_scheme FOREIGN KEY (scheme_id) REFERENCES scheme(id),
                                           CONSTRAINT fk_company_commission_created_by FOREIGN KEY (created_by) REFERENCES accounts(id),
                                           CONSTRAINT fk_company_commission_updated_by FOREIGN KEY (updated_by) REFERENCES accounts(id)
);


-- STEP 7: Create money_changer_photo table
CREATE TABLE money_changer_photo (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     money_changer_id INT NOT NULL,
                                     photo_data LONGBLOB NOT NULL,
                                     photo_filename VARCHAR(255),
                                     photo_mimetype VARCHAR(100),
                                     created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                     updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                     created_by INT,
                                     updated_by INT NULL,
                                     is_deleted TINYINT(1) DEFAULT 0,
                                     CONSTRAINT fk_photo_money_changer FOREIGN KEY (money_changer_id) REFERENCES money_changer(id),
                                     CONSTRAINT fk_photo_created_by FOREIGN KEY (created_by) REFERENCES accounts(id),
                                     CONSTRAINT fk_photo_updated_by FOREIGN KEY (updated_by) REFERENCES accounts(id)
);

CREATE TABLE money_changer_kyc (
                                   id INT AUTO_INCREMENT PRIMARY KEY,
                                   money_changer_id INT NOT NULL,
                                   document_data LONGBLOB NOT NULL,
                                   document_filename VARCHAR(255),
                                   document_mimetype VARCHAR(100),
                                   created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                   created_by INT,
                                   updated_by INT NULL,
                                   is_deleted TINYINT(1) DEFAULT 0,
                                   CONSTRAINT fk_kyc_money_changer FOREIGN KEY (money_changer_id) REFERENCES money_changer(id) ON DELETE CASCADE,
                                   CONSTRAINT fk_kyc_created_by FOREIGN KEY (created_by) REFERENCES accounts(id),
                                   CONSTRAINT fk_kyc_updated_by FOREIGN KEY (updated_by) REFERENCES accounts(id)
);



CREATE TABLE money_changer_location (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        money_changer_id INT NOT NULL,
                                        location_name VARCHAR(255) NOT NULL,
                                        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                        updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                        created_by INT,
                                        updated_by INT NULL,
                                        is_deleted TINYINT(1) DEFAULT 0,
                                        CONSTRAINT fk_location_money_changer FOREIGN KEY (money_changer_id) REFERENCES money_changer(id) ON DELETE CASCADE,
                                        CONSTRAINT fk_location_created_by FOREIGN KEY (created_by) REFERENCES accounts(id),
                                        CONSTRAINT fk_location_updated_by FOREIGN KEY (updated_by) REFERENCES accounts(id)
);



CREATE TABLE money_changer_currency (
                                   id INT AUTO_INCREMENT PRIMARY KEY,
                                   money_changer_id INT NOT NULL,
                                   currency_id INT NOT NULL,
                                   created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                   created_by INT,
                                   updated_by INT NULL,
                                   is_deleted TINYINT(1) DEFAULT 0,
                                   CONSTRAINT fk_mcur_money_changer FOREIGN KEY (money_changer_id) REFERENCES money_changer(id),
                                   CONSTRAINT fk_mcur_currency FOREIGN KEY (currency_id) REFERENCES currency_code(id),
                                   CONSTRAINT fk_mcur_created_by FOREIGN KEY (created_by) REFERENCES accounts(id),
                                   CONSTRAINT fk_mcur_updated_by FOREIGN KEY (updated_by) REFERENCES accounts(id)
);



CREATE TABLE customer (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          customer_name VARCHAR(255) NOT NULL,
                          password_hash VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL UNIQUE,
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          created_by INT,
                          updated_by INT NULL,
                          is_deleted TINYINT(1) DEFAULT 0,
                          CONSTRAINT fk_customer_created_by FOREIGN KEY (created_by) REFERENCES accounts(id),
                          CONSTRAINT fk_customer_updated_by FOREIGN KEY (updated_by) REFERENCES accounts(id)
);


CREATE TABLE transaction (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             transaction_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             customer_id INT NOT NULL,
                             current_status VARCHAR(20) NULL,
                             email VARCHAR(255),
                             comments TEXT,
                             money_changer_id INT NOT NULL,
                             currency_id INT NOT NULL,
                             exchange_rate DECIMAL(18,8) NOT NULL,
                             foreign_amount DECIMAL(18,8) NOT NULL,
                             sgd_amount DECIMAL(18,8) NOT NULL,
                             received_cash DECIMAL(18,8) NOT NULL,
                             created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             created_by INT,
                             updated_by INT NULL,
                             CONSTRAINT fk_transaction_customer FOREIGN KEY (customer_id) REFERENCES customer(id),
                             CONSTRAINT fk_transaction_money_changer FOREIGN KEY (money_changer_id) REFERENCES money_changer(id),
                             CONSTRAINT fk_transaction_currency FOREIGN KEY (currency_id) REFERENCES currency_code(id),
                             CONSTRAINT fk_transaction_created_by FOREIGN KEY (created_by) REFERENCES accounts(id),
                             CONSTRAINT fk_transaction_updated_by FOREIGN KEY (updated_by) REFERENCES accounts(id)
);




-- STEP 9: Add foreign keys (after all tables are created)
ALTER TABLE money_changer ADD CONSTRAINT fk_money_changer_scheme FOREIGN KEY (scheme_id) REFERENCES scheme(id);
ALTER TABLE money_changer ADD CONSTRAINT fk_money_changer_created_by FOREIGN KEY (created_by) REFERENCES accounts(id);
ALTER TABLE money_changer ADD CONSTRAINT fk_money_changer_updated_by FOREIGN KEY (updated_by) REFERENCES accounts(id);

ALTER TABLE accounts ADD CONSTRAINT fk_accounts_money_changer FOREIGN KEY (money_changer_id) REFERENCES money_changer(id);
ALTER TABLE accounts ADD CONSTRAINT fk_accounts_created_by FOREIGN KEY (created_by) REFERENCES accounts(id);
ALTER TABLE accounts ADD CONSTRAINT fk_accounts_updated_by FOREIGN KEY (updated_by) REFERENCES accounts(id);

ALTER TABLE scheme ADD CONSTRAINT fk_commission_scheme_created_by FOREIGN KEY (created_by) REFERENCES accounts(id);
ALTER TABLE scheme ADD CONSTRAINT fk_commission_scheme_updated_by FOREIGN KEY (updated_by) REFERENCES accounts(id);



