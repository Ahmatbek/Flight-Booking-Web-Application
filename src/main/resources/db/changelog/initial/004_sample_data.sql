INSERT INTO companies (name, email, password, logo, ACTIVE)
VALUES ('Air Kyrgyzstan', 'air.kyrgyzstan@example.com', '$2a$10$UC1ygbCyrbMW.Mj6MK4EqeHZuzKuq9lH25tYsACTPQcQrlTXlno52',
        'air_kyrgyzstan.png', true),
       ('Manas Airlines', 'manas.airlines@example.com', '$2a$10$UC1ygbCyrbMW.Mj6MK4EqeHZuzKuq9lH25tYsACTPQcQrlTXlno52',
        'manas_airlines.png', true),
       ('Tez Jet', 'tez.jet@example.com', '$2a$10$UC1ygbCyrbMW.Mj6MK4EqeHZuzKuq9lH25tYsACTPQcQrlTXlno52', 'tez_jet.png',
        true);

INSERT INTO tickets_class (name)
VALUES ('Economy'),
       ('Business'),
       ('First Class');

INSERT INTO flights (number, from_city, to_city, departure_time, arrival_time, company_id)
VALUES ('KG-101', 'Bishkek', 'Osh', '2024-04-01 08:00:00', '2024-04-01 09:00:00', 1),
       ('KG-102', 'Osh', 'Bishkek', '2024-04-01 10:00:00', '2024-04-01 11:00:00', 1),
       ('MA-201', 'Bishkek', 'Istanbul', '2024-04-02 06:00:00', '2024-04-02 10:00:00', 2),
       ('MA-202', 'Istanbul', 'Bishkek', '2024-04-03 12:00:00', '2024-04-03 16:00:00', 2),
       ('TJ-301', 'Bishkek', 'Moscow', '2024-04-04 07:00:00', '2024-04-04 11:00:00', 3),
       ('TJ-302', 'Moscow', 'Bishkek', '2024-04-05 13:00:00', '2024-04-05 17:00:00', 3);

INSERT INTO tickets (seat_number, price, flight_id, tickets_class_id, booked)
VALUES ('1A', 5000, 1, 1, false),
       ('1B', 5000, 1, 1, false),
       ('2A', 8000, 1, 2, false),
       ('2B', 8000, 1, 2, false),
       ('3A', 12000, 1, 3, false),
       ('3B', 12000, 1, 3, false),

       ('1A', 5000, 2, 1, false),
       ('1B', 5000, 2, 1, false),
       ('2A', 8000, 2, 2, false),
       ('2B', 8000, 2, 2, false),
       ('3A', 12000, 2, 3, false),
       ('3B', 12000, 2, 3, false),

       ('1A', 25000, 3, 1, false),
       ('1B', 25000, 3, 1, false),
       ('2A', 40000, 3, 2, false),
       ('2B', 40000, 3, 2, false),
       ('3A', 60000, 3, 3, false),
       ('3B', 60000, 3, 3, false),

       ('1A', 25000, 4, 1, false),
       ('1B', 25000, 4, 1, false),
       ('2A', 40000, 4, 2, false),
       ('2B', 40000, 4, 2, false),
       ('3A', 60000, 4, 3, false),
       ('3B', 60000, 4, 3, false),

       ('1A', 30000, 5, 1, false),
       ('1B', 30000, 5, 1, false),
       ('2A', 45000, 5, 2, false),
       ('2B', 45000, 5, 2, false),
       ('3A', 70000, 5, 3, false),
       ('3B', 70000, 5, 3, false),

       ('1A', 30000, 6, 1, false),
       ('1B', 30000, 6, 1, false),
       ('2A', 45000, 6, 2, false),
       ('2B', 45000, 6, 2, false),
       ('3A', 70000, 6, 3, false),
       ('3B', 70000, 6, 3, false);

INSERT INTO bookings (user_id, ticket_id, time)
VALUES (1, 1, '2024-03-20 10:00:00'),
       (2, 7, '2024-03-21 11:30:00'),
       (3, 13, '2024-03-22 09:45:00');