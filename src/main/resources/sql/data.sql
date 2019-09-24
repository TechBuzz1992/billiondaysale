-- password in plaintext: "password"
INSERT INTO USER (user_id, password, email, username, name, last_name, active)
VALUES
  (1, '$2a$06$OAPObzhRdRXBCbk7Hj/ot.jY3zPwR8n7/mfLtKIgTzdJa4.6TwsIm', 'harsh.dusane@gmail.com', 'harsh1992', 'harsh', 'dusane',
   1);
-- password in plaintext: "password"
INSERT INTO USER (user_id, password, email, username, name, last_name, active)
VALUES
  (2, '$2a$06$OAPObzhRdRXBCbk7Hj/ot.jY3zPwR8n7/mfLtKIgTzdJa4.6TwsIm', 'tarak.dusane@gmail.com', 'tarak1998', 'tarak', 'dusane', 1);

INSERT INTO ROLE (role_id, role)
VALUES (1, 'ROLE_ADMIN');
INSERT INTO ROLE (role_id, role)
VALUES (2, 'ROLE_USER');

INSERT INTO USER_ROLE (user_id, role_id)
VALUES (1, 1);
INSERT INTO USER_ROLE (user_id, role_id)
VALUES (1, 2);
INSERT INTO USER_ROLE (user_id, role_id)
VALUES (2, 2);

INSERT INTO PRODUCT (name, description, quantity, price,quantityType)
VALUES ('Soap', 'Pears baby soap for Kids', 1, 35.75,'UNLIMITED');
INSERT INTO PRODUCT (name, description, quantity, price,quantityType)
VALUES ('Tooth Brush', 'Signal Tooth Brushes Size in (L, M, S)', 5, 34.50,'LIMITED');
INSERT INTO PRODUCT (name, description, quantity, price,quantityType)
VALUES ('Shirt', 'Casual Shirt imported from France', 3, 1500.00,'UNLIMITED');
INSERT INTO PRODUCT (name, description, quantity, price,quantityType)
VALUES ('Office Bag', 'Leather bag imported from USA', 40, 1000.00,'UNLIMITED');
INSERT INTO PRODUCT (name, description, quantity, price,quantityType)
VALUES ('Bottle', 'Hot Water Bottles', 80, 450.45,'LIMITED');
INSERT INTO PRODUCT (name, description, quantity, price,quantityType)
VALUES ('Wrist Watch', 'Imported wrist watches from swiss', 800, 2500.00,'UNLIMITED');
INSERT INTO PRODUCT (name, description, quantity, price,quantityType)
VALUES ('Mobile Phone', '3G/4G capability', 700, 45000.00,'LIMITED');
INSERT INTO PRODUCT (name, description, quantity, price,quantityType)
VALUES ('Shampoo', 'Head and Shoulders Shampoo', 500, 300.00,'UNLIMITED');
INSERT INTO PRODUCT (name, description, quantity, price,quantityType)
VALUES ('Leather Wallets', 'Imported Leather Wallets from AUS', 1000, 500.00,'UNLIMITED');
INSERT INTO PRODUCT (name, description, quantity, price,quantityType)
VALUES ('Camera', 'Imported Canon camera from USA', 10, 85000.00,'LIMITED');