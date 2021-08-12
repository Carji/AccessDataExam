-- start a new transaction
USE DAOPizzaShop;

SET autocommit=0;

START TRANSACTION;

INSERT INTO ingredient (id, name, price) VALUES (UUID_TO_BIN(UUID()), 'TomatoSauce', 1.0);
INSERT INTO ingredient (id, name, price) VALUES (UUID_TO_BIN(UUID()), 'Cheese', 3.0);

INSERT INTO pizza (id, name, url) VALUES (UUID_TO_BIN(UUID()), 'Carbonara', 'randompic.jpg');

INSERT INTO pizza_ingredient (id, id_pizza, id_ingredient, ammount) VALUES (UUID_TO_BIN(UUID()), (SELECT id FROM pizza WHERE name='Carbonara'), (SELECT id FROM ingredient WHERE name='Cheese'), 1);

INSERT INTO pizza_ingredient (id, id_pizza, id_ingredient, ammount) VALUES (UUID_TO_BIN(UUID()), (SELECT id FROM pizza WHERE name='Carbonara'), (SELECT id FROM ingredient WHERE name='TomatoSauce'), 1);

-- commit changes 
COMMIT;

