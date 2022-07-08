CREATE SCHEMA IF NOT EXISTS PhoneContacts DEFAULT CHARACTER SET utf8 ;
USE PhoneContacts ;

-- -----------------------------------------------------
-- Tabla contacto
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS contact(
  con_id INT NOT NULL AUTO_INCREMENT  PRIMARY KEY,
  con_full_name VARCHAR(45) NOT NULL,
  con_phone VARCHAR(15) NOT NULL,
  con_email VARCHAR(45) NOT NULL,
  con_birthday VARCHAR(15) NOT NULL

)ENGINE = InnoDB;

-- -----------------------------------------------------
-- Insercion de información Tabla contacto
-- -----------------------------------------------------
INSERT INTO contact (con_full_name, con_phone, con_email, con_birthday)
VALUES
 ('Juan Pablo Martinez', '3104567890','juanpama@gmail.com', '23-01-1999');
/*  ('Rosa Perez'),
 ('Camilo Urueña'),
 ('Pedro Salinas'),
 ('Alirio Moya'); */

-- -----------------------------------------------------
-- Insercion de información Tabla venta
-- -----------------------------------------------------
INSERT INTO sale (sale_amount_product)
VALUES
 (10),
 (20),
 (20),
 (5),
 (4),
 (4);

-- -----------------------------------------------------
-- Insercion de información Tabla cliente
-- -----------------------------------------------------
INSERT INTO client (sale_sale_id, cli_document_type, cli_document_number)
VALUES
 (1, 'CC', '1001010101'),
 (2, 'CC', '1992020200'),
 (3, 'CC', '3388383990'),
 (4, 'TI', '1299200300');

 -- -----------------------------------------------------
-- Insercion de información Tabla producto
-- -----------------------------------------------------
INSERT INTO product (provider_prov_id, sale_sal_id, pro_name)
VALUES
 (1, 2, 'Arroz'),
 (2, 1, 'Frijol'),
 (4, 3, 'Azucar'),
 (5, 4, 'Sal'),
 (3, 5, 'Cafe');

-- 3.REALIZAR DOS BORRADOS LÓGICOS Y DOS BORRADOS FISICOS
--  DE VENTAS REALIZADAS


-- -----------------------------------------------------
-- Borrado logico
-- -----------------------------------------------------
ALTER TABLE sale ADD (
    state VARCHAR(10) default 'Activo' 
);

UPDATE sale 
SET state = 'Inactivo' 
WHERE sale_id = 2;

UPDATE sale 
SET state = 'Inactivo' 
WHERE sale_id = 6;

SELECT (sale_id) FROM sale 
WHERE state <> 'Inactivo';

-- -----------------------------------------------------
-- Borrado fisico
-- -----------------------------------------------------
DELETE FROM sale 
WHERE sale_id =1;

DELETE FROM sale WHERE 
sale_id =4;

-- 4.MODIFICAR TRES PRODUCTOS EN SU NOMBRE Y PROVEEDOR
--  QUE LOS PROVEE

UPDATE product
INNER JOIN provider ON
product.provider_prov_id= provider.prov_id
SET product.pro_name ='Frijol', provider.prov_name= 'Juan Carlos Martinez' 
WHERE pro_id= 11;

UPDATE product
INNER JOIN provider ON
product.provider_prov_id= provider.prov_id
SET product.pro_name ='Sal', provider.prov_name= 'Adriana Salinas' 
WHERE pro_id= 13;

UPDATE product
INNER JOIN provider ON
product.provider_prov_id= provider.prov_id
SET product.pro_name ='Chocolate', provider.prov_name= 'Juan Perez' 
WHERE pro_id= 15;