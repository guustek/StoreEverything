INSERT INTO app_user (email,password)
VALUES ('JD','2137');

INSERT INTO category (name)
VALUES('Zakupy');
INSERT INTO category (name)
VALUES('Link');

INSERT INTO information (title, content, category_id, added_date, remind_date)
VALUES ('Pieczywo', 'Bułki - 2x, Chleb - 1x', 1, '2022-04-28', '2022-04-30');
INSERT INTO information (title, content, link, category_id, added_date, remind_date)
VALUES ('Google', 'Link do google', 'www.google.com', 2, '2022-04-28', '2022-04-30');