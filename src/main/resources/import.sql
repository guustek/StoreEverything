INSERT INTO category (id,name)
VALUES(1,'Zakupy');
INSERT INTO category (id,name)
VALUES(2,'Link');

INSERT INTO information (id, title, content, category_id, added_date, remind_date)
VALUES (1, 'Pieczywo', 'Bułki - 2x, Chleb - 1x', 1, '2022-04-28', '2022-04-30');
INSERT INTO information (id, title, content, link, category_id, added_date, remind_date)
VALUES (2, 'Google', 'Link do google', 'www.google.com', 2, '2022-04-28', '2022-04-30');