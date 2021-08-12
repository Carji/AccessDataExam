package PizzaDAO;

import java.sql.*;
import java.util.*;

import Domain.Ingredient;
import Domain.Pizza;

public class PizzaDao implements DaoW<Pizza>, DaoR<Pizza> {

    final String insertPizza = """
                                INSERT INTO pizza (id, name, url) 
                                VALUES (UUID_TO_BIN(?), ?, ?)
                               """;
    final String insertIngr = """
                                INSERT INTO pizza_ingredient (id, id_pizza, id_ingredient)
                                VALUES (UUID_TO_BIN(UUID()),
                                (SELECT id FROM pizza WHERE name=?),
                                (SELECT id FROM ingredient WHERE name=?));
                              """;

    final String getAllPizza = "SELECT id, name, url FROM pizza";
    final String getOnePizza = "SELECT id, name, url FROM pizza WHERE id = ?";

    final String updatePizza = "UPDATE pizza SET name = ?, url = ? WHERE id = ?";
    final String deletePizza = "DELETE FROM pizza WHERE id = ?";


    static final String DB_URL = System.getenv("DB_URL");
    static final String USER = System.getenv("user");
    static final String PASS = System.getenv("password");


    public void insert(Pizza pizza) {
        try {
            Connection connect = DriverManager.getConnection(DB_URL+"DAOPizzaShop", USER, PASS);
            connect.createStatement();
            PreparedStatement stPizza = connect.prepareStatement(insertPizza);

            stPizza.setBytes(1,UuidAdapter.getBytesFromUUID(pizza.getId()));//pizza.getId().toString());
            stPizza.setString(2, pizza.getName());
            stPizza.setString(3, pizza.getUrl());

            stPizza.executeUpdate();


            connect.createStatement();
            PreparedStatement stPizza2 = connect.prepareStatement(insertIngr);
            Set<Ingredient> ingredients = pizza.getIngredients();
            Iterator<Ingredient> iterator = ingredients.iterator();
            while(iterator.hasNext()){
                Ingredient ingredient = iterator.next();
                stPizza2.setString(1, pizza.getName());
                stPizza2.setString(2, ingredient.getName());
                stPizza2.addBatch();
            }
            stPizza2.executeUpdate();
            connect.close();


            System.out.println(pizza.getName() + " pizza has been saved correctly.");}
        catch (SQLException excep) {
            excep.printStackTrace();
            System.out.println("Error saving the pizza.");
        }

    
    }


    public void update(Pizza pizza) {
        try (Connection connect = DriverManager.getConnection(DB_URL+"DAOPizzaShop", USER, PASS);
        Statement stmn = connect.createStatement();){
            PreparedStatement stPizza = connect.prepareStatement(updatePizza);

            stPizza.setString(1, pizza.getName());
            stPizza.setString(2, pizza.getUrl());
            stPizza.setBytes(3,UuidAdapter.getBytesFromUUID(pizza.getId()));

            stPizza.executeUpdate();
            connect.close();
            System.out.println(pizza.getName() + " pizza has been updated correctly.");}
       
        catch (SQLException excep) {
            excep.printStackTrace();
            System.out.println("Error updating the pizza.");
        }
    }


    public void delete(Pizza pizza) {
        try (Connection connect = DriverManager.getConnection(DB_URL+"DAOPizzaShop", USER, PASS);
        Statement stmn = connect.createStatement();){
            PreparedStatement stPizza = connect.prepareStatement(deletePizza);

            stPizza.setBytes(1,UuidAdapter.getBytesFromUUID(pizza.getId()));
            stPizza.executeUpdate();
            System.out.println(pizza.getName() + " pizza has been deleted correctly.");
            connect.close();

        }catch (Exception excep) {
            excep.printStackTrace();
            System.out.println("Error deleting the pizza.");
        }
    }


//     public ResultSet get(Pizza pizza) {
//         ResultSet result=null;
//         Pizza pizzaRes = null;
//         try (Connection connect = DriverManager.getConnection(DB_URL+"DAOPizzaShop", USER, PASS);
//         Statement stmn = connect.createStatement();){
//             PreparedStatement stPizza = connect.prepareStatement(getOnePizza);

//             stPizza.setString(1, pizza.getId().toString());

//             result=stPizza.executeQuery();
            
// //            UUID uuid = pizza.getId();

//             while (result.next()) {
//             System.out.println(((ResultSet) result).getObject("id")+ "," 
//             + ((ResultSet) result).getString("name") + ", "
//             + ((ResultSet) result).getString("url") + ".");

//             String name = result.getString("name");
//             String url = result.getString("url");
//             pizzaRes = new Pizza(name, url);
//             pizzaRes.setId(pizza.getId());

//         }

//         } catch (SQLException excep) {
//             excep.printStackTrace();
//             System.out.println("Error selecting the pizza.");
//         }
//         return result;
//     }

//      public Pizza get(UUID id) {
//         ArrayList<Pizza> listaPizzas = new ArrayList<Pizza>();
//          try (Connection connect = DriverManager.getConnection(DB_URL+"DAOPizzaShop", USER, PASS);
//             Statement stmn = connect.createStatement();){
//             PreparedStatement selectPizza = connect
//             .prepareStatement("SELECT id, name, url FROM pizza WHERE id = ? ;");
//             selectPizza.setBytes(1, UuidAdapter.getBytesFromUUID(id));

//             ResultSet rs = selectPizza.executeQuery();
//             System.out.println(rs);
//             while (rs.next()) {
//                 listaPizzas.add(new Pizza(rs.getString(2), rs.getString(3)));
//             }
//             System.out.println(listaPizzas.get(0).getName());
//             return listaPizzas;
//         } catch (SQLException excep) {
//             excep.printStackTrace();
//         return listaPizzas;
//     }
// }

    public Pizza get(UUID id)  {

        ArrayList<Pizza> listaPizzas = new ArrayList<Pizza>();
        try (Connection connect = DriverManager.getConnection(DB_URL+"DAOPizzaShop", USER, PASS);
        Statement stmn = connect.createStatement();){
            PreparedStatement selectPizza = connect.prepareStatement(getOnePizza);
            selectPizza.setBytes(1, UuidAdapter.getBytesFromUUID(id));
            ResultSet rs = selectPizza.executeQuery();
            System.out.println(rs);
            while (rs.next()) {
                listaPizzas.add(new Pizza(rs.getString(2), rs.getString(3)));
            }
            System.out.println(listaPizzas.get(0).getName());
            return listaPizzas.get(0);
        } catch (SQLException excep) {
            excep.printStackTrace();
            return listaPizzas.get(0);
        }
    }


    // public Pizza getAll() {
    //     ResultSet result=null;
    //     try (Connection connect = DriverManager.getConnection(DB_URL+"DAOPizzaShop", USER, PASS);
    //     Statement stmn = connect.createStatement();){
    //         PreparedStatement stPizza = connect.prepareStatement(getAllPizza);
    //         result=stPizza.executeQuery();
        
    //         while (result.next()) {

    //         System.out.println(((ResultSet) result).getObject("id")+ "," 
    //         + ((ResultSet) result).getString("name") + ", "
    //         + ((ResultSet) result).getString("url") + ".");
    //         }

    //     } catch (SQLException excep){
    //         excep.printStackTrace();
    //         System.out.println("Error selecting the pizzas.");
    //     }
    //     return result;
    // }

    public Pizza getAll()  {

        List<Pizza> listaPizzas = new ArrayList<Pizza>();
        try (Connection connect = DriverManager.getConnection(DB_URL+"DAOPizzaShop", USER, PASS);
        Statement stmn = connect.createStatement();){
            PreparedStatement selectPizza = connect.prepareStatement(getAllPizza);

            ResultSet rs = selectPizza.executeQuery();
            System.out.println(rs);

            while (rs.next()) {
                listaPizzas.add(new Pizza(rs.getString(2), rs.getString(3)));
            }
            System.out.println(listaPizzas.get(0).getName());
            return listaPizzas.get(0);
        } catch (SQLException excep) {
            excep.printStackTrace();
            System.out.println("Error selecting the pizzas.");
            return listaPizzas.get(0);
        }
    }
}
