CREATE SCHEMA IF NOT EXISTS phonecontacts DEFAULT CHARACTER SET utf8 ;
USE phonecontacts;

-- -----------------------------------------------------
-- Tabla contacto
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS contact(
  con_id INT UNSIGNED NOT NULL AUTO_INCREMENT  PRIMARY KEY,
  con_full_name VARCHAR(45) NOT NULL,
  con_email VARCHAR(45) NOT NULL,
  con_birthday VARCHAR(45) NOT NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE INDEX contact_con_full_name_Idx ON contact (con_full_name) USING BTREE;
CREATE INDEX contact_con_email_Idx ON contact(con_email) USING BTREE;
CREATE INDEX contact_con_birthday_Idx ON contact(con_birthday) USING BTREE;
CREATE UNIQUE INDEX contact_con_full_name_con_email_con_birthday_Idx ON contact (con_full_name, con_email, con_birthday ) USING BTREE;
-- -----------------------------------------------------
-- Tabla telefono
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS phone(
  pho_id INTEGER UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL,
  pho_contact_id INT UNSIGNED NOT NULL,
  pho_number VARCHAR(15) NOT NULL
  
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE UNIQUE INDEX phone_pho_contact_id_pho_phone_Idx ON phone (pho_contact_id, pho_number) USING BTREE;
CREATE INDEX phone_pho_phone_Idx ON phone (pho_number) USING BTREE;
CREATE INDEX phone_pho_contact_id_Idx ON phone (pho_contact_id) USING BTREE;



ALTER TABLE phone ADD CONSTRAINT fk_phone_contact
FOREIGN KEY (pho_contact_id) REFERENCES contact (con_id)
ON UPDATE CASCADE ON DELETE RESTRICT;
-- -----------------------------------------------------
-- Insercion de información Tabla contacto
-- -----------------------------------------------------
INSERT INTO contact (con_full_name, con_email, con_birthday)
VALUES
 ('Juan Pablo Martinez','juanpama@gmail.com', '1989-03-04');

INSERT INTO phone (pho_contact_id, pho_number)
VALUES
('1','232422')