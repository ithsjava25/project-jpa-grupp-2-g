DROP DATABASE IF EXISTS testdb;
CREATE DATABASE testdb;
USE testdb;
DROP TABLE IF EXISTS Booking;
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS DiningTable;
DROP TABLE IF EXISTS Restaurant;

CREATE TABLE Booking
(
    booking_end    TIME,
    booking_start       TIME,
    date   DATE,
    customer_id bigint,
    id  bigint AUTO_INCREMENT PRIMARY KEY ,
    restaurant_id        bigint,
    table_id        bigint
);
CREATE TABLE Customer
(
    customerId     bigint AUTO_INCREMENT PRIMARY KEY,
    email     VARCHAR(255),
    firstName    VARCHAR(255),
    lastName VARCHAR(255),
    phoneNumber       VARCHAR(255)
);
CREATE TABLE DiningTable
(
    capacity     int,
    table_number     int,
    id    bigint AUTO_INCREMENT PRIMARY KEY ,
    restaurant_id bigint
);
CREATE TABLE Restaurant
(
    mean_price     decimal,
    rating     double,
    id    bigint AUTO_INCREMENT PRIMARY KEY ,
    address VARCHAR(255),
    category VARCHAR(255),
    imagePath VARCHAR(255),
    name VARCHAR(255)
);


ALTER TABLE Restaurant
    ADD CONSTRAINT uq_restaurant_name UNIQUE (name);

ALTER TABLE DiningTable
    ADD CONSTRAINT uq_diningtable UNIQUE (restaurant_id, table_number);

ALTER TABLE Customer
    ADD CONSTRAINT uq_customer_phoneNumber UNIQUE (phoneNumber);

INSERT INTO Restaurant (name, category, address, rating, mean_price, ImagePath) VALUES
('Pizzeria Napoli','Pizza','Storgatan 12',4.5,120.0, 'pizzeriaNapoli.png'),
('Sushi Yama','Sushi','Kungsgatan 5',4.2,180.0, 'sushiYama.png'),
('Burger House','Hamburger','Sveavägen 44',4.0,140.0, 'burgerHouse.png'),
('Green Garden','Vegetarian','Odengatan 21',4.6,160.0, 'greenGarden.png'),
('Curry Palace','Indian','Hornsgatan 88',4.3,150.0, 'curryPalace.png'),
('TOSO', 'Asian', 'Götaplatsen 1', 4.9, 400, 'toso.png'),
('Lilla Taverna', 'Greek', 'Olivedalsgatan 17', 3.7, 180, 'lillaTaverna.png');


INSERT INTO Customer (firstName, lastName, phoneNumber, email) VALUES
('Anna','Svensson','0701111111','anna@mail.se'),
('Erik','Larsson','0702222222','erik@mail.se'),
('Sara','Nilsson','0703333333','sara@mail.se'),
('Johan','Karlsson','0704444444','johan@mail.se'),
('Emma','Andersson','0705555555','emma@mail.se');


INSERT INTO DiningTable (table_number, capacity, restaurant_id) VALUES
-- Restaurant 1
(1,2,1),
(2,4,1),
(3,6,1),
(4,6,1),
(5,8,1),
-- Restaurant 2
(1,2,2),
(2,4,2),
(3,6,2),
(4,6,2),
(5,8,2),
-- Restaurant 3
(1,2,3),
(2,4,3),
(3,6,3),
(4,6,3),
(5,8,3),
-- Restaurant 4
(1,2,4),
(2,4,4),
(3,6,4),
(4,6,4),
(5,8,4),
-- Restaurant 5
(1,2,5),
(2,4,5),
(3,6,5),
(4,6,5),
(5,8,5),
-- Restaurant 6
(1,2,6),
(2,4,6),
(3,6,6),
(4,6,6),
(5,8,6),
-- Restaurant 7
(1,2,7),
(2,4,7),
(3,6,7),
(4,6,7),
(5,8,7);

