SET autocommit=0;

USE DAOPizzaShop;

START TRANSACTION;

INSERT INTO pizza (id, name, url) VALUES (UUID_TO_BIN(UUID()), 'Margherita', 'randompic.png');
INSERT INTO pizza (id, name, url) VALUES (UUID_TO_BIN(UUID()), 'BBQ', 'randomlink.jpg');

COMMIT;

SELECT pizza.id, pizza.name, ROUND(SUM(ingredient.price),2) AS price 
FROM pizza 
LEFT JOIN pizza_ingredient ON pizza.id = pizza_ingredient.id_pizza
LEFT JOIN ingredient ON pizza_ingredient.id_ingredient = ingredient.id
WHERE price IS NULL
GROUP BY pizza.id, pizza.name, url;
