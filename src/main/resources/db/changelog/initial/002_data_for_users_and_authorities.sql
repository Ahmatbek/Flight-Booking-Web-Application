INSERT INTO authority (name) VALUES ('USER');
INSERT INTO authority (name) VALUES ('ADMIN');
INSERT INTO authority (name) VALUES ('COMPANY');

INSERT INTO users (email,name, password, enabled, authority_id)
VALUES ('user1@example.com','ahmatbek', '$2a$10$UC1ygbCyrbMW.Mj6MK4EqeHZuzKuq9lH25tYsACTPQcQrlTXlno52', true,
        (SELECT id FROM authority WHERE name = 'USER'));

INSERT INTO users (email, name,  password, enabled, authority_id)
VALUES ('user2@example.com','tilek', '$2a$10$UC1ygbCyrbMW.Mj6MK4EqeHZuzKuq9lH25tYsACTPQcQrlTXlno52', true,
        (SELECT id FROM authority WHERE name = 'USER'));

INSERT INTO users (email, name, password, enabled, authority_id)
VALUES ('admin@example.com', 'burulai','$2a$10$UC1ygbCyrbMW.Mj6MK4EqeHZuzKuq9lH25tYsACTPQcQrlTXlno52', true,
        (SELECT id FROM authority WHERE name = 'ADMIN'));
