INSERT INTO category (name)
VALUES ('zakupy');
INSERT INTO category (name)
VALUES ('link');

INSERT INTO app_user(email, enabled, locked, password, role, name, surname, age)
VALUES ('admin', true, false, '$2a$10$JEDkM6l7j5K5rT6deXIEO.vgC15..4ISSmzNWzjdWANmNpn32rsre', 'ROLE_ADMIN', 'Potężny',
        'Admin', 18);

INSERT INTO app_user(email, enabled, locked, password, role, name, surname, age)
VALUES ('limited', true, false, '$2a$10$gU6wAbbOx3iqEU0pqIGk0OiQboUtlYSa.TFJoVVxtzRIPDwz/535u', 'ROLE_LIMITED_USER',
        'Grzegorz', 'Floryda', 18);

INSERT INTO app_user(email, enabled, locked, password, role, name, surname, age)
VALUES ('full', true, false, '$2a$10$noeuc0BAYiJ4G.YTgQT6E.gDcZrcXdwO81jluPo/pKZ3KBdPCG7fC', 'ROLE_FULL_USER', 'Jan',
        'Paweł', 2137);

INSERT INTO information (title, content, category_id, added_date, remind_date, user_id)
VALUES ('Pieczywo', 'Bułki - 2x, Chleb - 1x', 1, '2022-04-28', '2022-04-30', 1);

INSERT INTO information (title, content, link, category_id, added_date, remind_date, user_id)
VALUES ('Google', 'Link do google', 'https://www.google.com', 2, '2022-04-28', '2022-06-19', 1);

INSERT INTO information (title, content, link, category_id, added_date, remind_date, user_id)
VALUES ('XD', 'Link TEST', 'https://XD.com', 2, '2022-04-28', '2022-06-19', 1);

INSERT INTO information (title, content, category_id, remind_date, user_id)
VALUES ('Wadowice', 'Kremówki', 2, '2022-06-22', 3);