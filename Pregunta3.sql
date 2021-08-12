
USE DAOPizzaShop;

SELECT pizza.id, pizza.name, ROUND(SUM(ingredient.price*ammount),2) AS priceInEuros 
FROM pizza 
LEFT JOIN pizza_ingredient ON pizza.id = pizza_ingredient.id_pizza
LEFT JOIN ingredient ON pizza_ingredient.id_ingredient = ingredient.id
GROUP BY pizza.id, pizza.name, url;