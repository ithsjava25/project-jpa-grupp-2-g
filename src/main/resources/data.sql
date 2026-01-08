
INSERT INTO Restaurant (id, name, category, address, price_range, rating, mean_price) VALUES
(1,'Pizzeria Napoli','Pizza','Storgatan 12',2.0,4.5,120.0),
(2,'Sushi Yama','Sushi','Kungsgatan 5',3.0,4.2,180.0),
(3,'Burger House','Burgare','Sveavägen 44',2.0,4.0,140.0),
(4,'Green Garden','Vegetariskt','Odengatan 21',3.0,4.6,160.0),
(5,'Curry Palace','Indiskt','Hornsgatan 88',2.5,4.3,150.0);


INSERT INTO Customer (firstName, lastName, phoneNumber, email) VALUES
('Anna','Svensson','0701111111','anna@mail.se'),
('Erik','Larsson','0702222222','erik@mail.se'),
('Sara','Nilsson','0703333333','sara@mail.se'),
('Johan','Karlsson','0704444444','johan@mail.se'),
('Emma','Andersson','0705555555','emma@mail.se');

INSERT INTO DiningTable (table_number, capacity, restaurant_id) VALUES
(1,2,1),(2,4,1),(3,6,1),
(1,2,2),(2,4,2),
(1,2,3),(2,4,3),
(1,2,4),(2,4,4),
(1,2,5),(2,4,5);

INSERT INTO Booking (booking_start, booking_end, customer_id, restaurant_id) VALUES
('2026-01-10 18:00:00','2026-01-10 20:00:00',1,1),
('2026-01-11 18:00:00','2026-01-11 20:00:00',2,2),
('2026-01-12 18:00:00','2026-01-12 20:00:00',3,3),
('2026-01-13 18:00:00','2026-01-13 20:00:00',4,4),
('2026-01-14 18:00:00','2026-01-14 20:00:00',5,5);
