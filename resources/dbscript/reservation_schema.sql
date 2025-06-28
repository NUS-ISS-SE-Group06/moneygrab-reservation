use reservationdb;


CREATE TABLE reservation (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             customer_id INT NOT NULL,
                             money_changer_id INT NOT NULL,
                             currency_id INT NOT NULL,
                             exchange_rate DECIMAL(18,8) NOT NULL,
                             foreign_amount DECIMAL(18,8) NOT NULL,
                             sgd_amount DECIMAL(18,8) NOT NULL,
                             status VARCHAR(20) NOT NULL, -- e.g., PENDING, CONFIRMED, EXPIRED, CANCELLED
                             expires_at TIMESTAMP NOT NULL, -- expiry time of reservation
                             created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             created_by INT,
                             updated_by INT NULL
);

