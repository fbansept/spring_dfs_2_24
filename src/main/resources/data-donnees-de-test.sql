INSERT INTO category (nom) VALUES
("Cafe"),
("Consommable");

INSERT INTO product (nom,prix, category_id) VALUES
("Expresso", 3.5, 1),
("Latte", 2, 1),
("Cappuccino", 4.5, 1),
("Sucrette", 0.1, 2);

INSERT INTO tag (nom) VALUES
("Best seller"),
("Special Noël"),
("En promo");

INSERT INTO product_tag (product_id, tag_id) VALUES
(1, 1), (4, 3), (1, 3);