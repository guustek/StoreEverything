INSERT INTO category (name)
VALUES ('zakupy');
INSERT INTO category (name)
VALUES ('link');

INSERT INTO app_user(email, enabled, locked, password)
VALUES ('admin', true, false, '$2a$10$JEDkM6l7j5K5rT6deXIEO.vgC15..4ISSmzNWzjdWANmNpn32rsre');

INSERT INTO information (title, content, category_id, added_date, remind_date, user_id)
VALUES ('Pieczywo', 'Bułki - 2x, Chleb - 1x', 1, '2022-04-28', '2022-04-30', 1);
INSERT INTO information (title, content, link, category_id, added_date, remind_date, user_id)
VALUES ('Google', 'Link do google', 'www.google.com', 2, '2022-04-28', '2022-04-30', 1);