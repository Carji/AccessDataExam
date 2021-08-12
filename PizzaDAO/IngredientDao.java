package PizzaDAO;

import java.sql.*;
import java.util.*;

import Domain.Ingredient;

public class IngredientDao implements DaoW<Ingredient>, DaoR<Ingredient> {

    final String insertIng = """
                                INSERT INTO ingredient (id, name, price) 
                                VALUES (UUID_TO_BIN(?), ?, ?)
                            """;

    final String getAllIng = "SELECT id, name, price FROM ingredient";
    final String getOneIng = "SELECT id, name, price FROM ingredient WHERE id = UUID_TO_BIN(?)";

    final String updateIng = """
                                UPDATE ingredient SET name = ?, price = ? 
                                WHERE id = UUID_TO_BIN(?)
                            """;
    final String deleteIng = "DELETE FROM ingredient WHERE id = UUID_TO_BIN(?)";


    static final String DB_URL = System.getenv("DB_URL");
    static final String USER = System.getenv("user");
    static final String PASS = System.getenv("password");


    public void insert(Ingredient ingr) {
        try (Connection connect = DriverManager.getConnection(DB_URL+"DAOPizzaShop", USER, PASS);
        Statement stmn = connect.createStatement();){
            PreparedStatement stIng = connect.prepareStatement(insertIng);

            stIng.setString(1, ingr.getId().toString());
            stIng.setString(2, ingr.getName());
            stIng.setFloat(3, ingr.getPrice());

            stIng.executeUpdate();
            System.out.println(ingr.getName() + " ingredient has been saved correctly.");}
        catch (SQLException excep) {
            excep.printStackTrace();
            System.out.println("Error saving the ingredient.");
        }
    }


    public void update(Ingredient ingr) {
        try (Connection connect = DriverManager.getConnection(DB_URL+"DAOPizzaShop", USER, PASS);
        Statement stmn = connect.createStatement();){
            PreparedStatement stIng = connect.prepareStatement(updateIng);

            stIng.setString(1, ingr.getName());
            stIng.setFloat(2, ingr.getPrice());
            stIng.setString(3, ingr.getId().toString());

            stIng.executeUpdate();
            System.out.println(ingr.getName() + " ingredient has been updated correctly.");}
       
        catch (SQLException excep) {
            excep.printStackTrace();
            System.out.println("Error updating the ingredient.");
        }
    }


    public void delete(Ingredient ingr) {
        try (Connection connect = DriverManager.getConnection(DB_URL+"DAOPizzaShop", USER, PASS);
        Statement stmn = connect.createStatement();){
            PreparedStatement stIng = connect.prepareStatement(deleteIng);

            stIng.setString(1, ingr.getId().toString());
            stIng.executeUpdate();
            System.out.println(ingr.getName() + " ingredient has been deleted correctly.");

        }catch (Exception excep) {
            excep.printStackTrace();
            System.out.println("Error deleting the ingredient.");
        }
    }




    public Ingredient get(UUID id)  {

        ArrayList<Ingredient> listaIngredient = new ArrayList<Ingredient>();
        try (Connection connect = DriverManager.getConnection(DB_URL+"DAOPizzaShop", USER, PASS);
        Statement stmn = connect.createStatement();){
            PreparedStatement selectIng = connect.prepareStatement(getOneIng);
            selectIng.setBytes(1, UuidAdapter.getBytesFromUUID(id));
            ResultSet result = selectIng.executeQuery();
            System.out.println(result);
            while (result.next()) {
                listaIngredient.add(new Ingredient(result.getString(2), result.getFloat(3)));
            }
            System.out.println(listaIngredient.get(0).getName());
            return listaIngredient.get(0);
        } catch (SQLException excep) {
            excep.printStackTrace();
            System.out.println("Error selecting the ingredient.");
            return listaIngredient.get(0);
        }
    }


    public Ingredient getAll()  {
        ArrayList<Ingredient> listaIngredient = new ArrayList<Ingredient>();
        try (Connection connect = DriverManager.getConnection(DB_URL+"DAOPizzaShop", USER, PASS);
        Statement stmn = connect.createStatement();){
            PreparedStatement selectIng = connect.prepareStatement(getAllIng);

            ResultSet result = selectIng.executeQuery();
            System.out.println(result);

            while (result.next()) {
                listaIngredient.add(new Ingredient(result.getString(2), result.getFloat(3)));
            }
            System.out.println(listaIngredient.get(0).getName());
            return listaIngredient.get(0);
        } catch (SQLException excep) {
            excep.printStackTrace();
            System.out.println("Error selecting the pizzas.");
            return listaIngredient.get(0);
        }
    }

}