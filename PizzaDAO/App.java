package PizzaDAO;

import java.util.UUID;

import Domain.Pizza;

public class App {
    public static void main(String[] args) {
        PizzaDao pizzaDao = new PizzaDao();

        Pizza pizza = new Pizza("BBQ6","randompic.png");

        UUID uuid = UUID.randomUUID();
        pizza.setId(uuid);  

        System.out.println(pizza.getId());

        pizza.setUrl("nuevaurl.png");
        pizzaDao.insert(pizza);

        pizza.setUrl("nuevaurl2.png");
        pizza.setName("Pepperoni");
        pizzaDao.update(pizza);



//        pizzaDao.getAll();

        pizzaDao.get(pizza.getId());

        pizzaDao.delete(pizza);
    }
}
