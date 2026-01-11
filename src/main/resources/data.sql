INSERT INTO Restaurant (name, category, address, rating, mean_price, ImagePath) VALUES
('Pizzeria Napoli','Pizza','Storgatan 12',4.5,120.0, 'pizzeriaNapoli.png'),
('Sushi Yama','Sushi','Kungsgatan 5',4.2,180.0, 'sushiYama.png'),
('Burger House','Hamburger','Sveavägen 44',4.0,140.0, 'burgerHouse.png'),
('Green Garden','Vegetarian','Odengatan 21',4.6,160.0, 'greenGarden.png'),
('Curry Palace','Indian','Hornsgatan 88',4.3,150.0, 'curryPalace.png'),
('TOSO', 'Asian', 'Götaplatsen 1', 4.9, 400, 'toso.png'),
('Lilla Taverna', 'Greek', 'Olivedalsgatan 17', 3.7, 180, 'lillaTaverna.png')


INSERT INTO Customer (firstName, lastName, phoneNumber, email) VALUES
('Anna','Svensson','0701111111','anna@mail.se'),
('Erik','Larsson','0702222222','erik@mail.se'),
('Sara','Nilsson','0703333333','sara@mail.se'),
('Johan','Karlsson','0704444444','johan@mail.se'),
('Emma','Andersson','0705555555','emma@mail.se');


INSERT INTO DiningTable (table_number, capacity, restaurant_id) VALUES
-- Restaurant 1
(1,2,1),(2,4,1),(3,6,1),
-- Restaurant 2
(1,2,2),(2,4,2),(3,6,2),
-- Restaurant 3
(1,2,3),(2,4,3),(3,6,3),
-- Restaurant 4
(1,2,4),(2,4,4),(3,6,4),
-- Restaurant 5
(1,2,5),(2,4,5),(3,6,5);


INSERT INTO Booking (booking_start, booking_end, customer_id, restaurant_id) VALUES
('2026-01-10 18:00:00','2026-01-10 20:00:00',1,1),
('2026-01-11 18:00:00','2026-01-11 20:00:00',2,2),
('2026-01-12 18:00:00','2026-01-12 20:00:00',3,3),
('2026-01-13 18:00:00','2026-01-13 20:00:00',4,4),
('2026-01-14 18:00:00','2026-01-14 20:00:00',5,5);
