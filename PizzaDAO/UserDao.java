package PizzaDAO;

import java.sql.*;
import java.util.*;

import Domain.User;

public class UserDao implements DaoW<User>, DaoR<User> {

    final String insertUser = "INSERT INTO user (id, name, lastname, email, password) VALUES (UUID_TO_BIN(UUID()), ?, ?, ?, ?)";

    final String getAllUser = "SELECT id, name, lastname, email, password FROM user";
    final String getOneUser = "SELECT id, name, lastname, email, password FROM user WHERE email = ?";

    final String updateUser = "UPDATE user SET name = ?, lastname = ?, email = ?, password = ? WHERE id = UUID_TO_BIN(UUID())";
    final String deleteUser = "DELETE FROM user WHERE email = ?";


    static final String DB_URL = System.getenv("DB_URL");
    static final String USER = System.getenv("user");
    static final String PASS = System.getenv("password");


    public void insert(User u) {
        try (Connection connect = DriverManager.getConnection(DB_URL+"DAOPizzaShop", USER, PASS);
        Statement stmn = connect.createStatement();){
            PreparedStatement stUser = connect.prepareStatement(insertUser);

            stUser.setString(1, u.getName());
            stUser.setString(2, u.getLastname());
            stUser.setString(3, u.getEmail());
            stUser.setString(4, u.getPassword());

            stUser.executeUpdate();
            System.out.println(u.getName() + "user has been saved correctly.");}
        catch (SQLException excep) {
            excep.printStackTrace();
            System.out.println("Error saving the user.");
        }
    }


    public void update(User u) {
        try (Connection connect = DriverManager.getConnection(DB_URL+"DAOPizzaShop", USER, PASS);
        Statement stmn = connect.createStatement();){
            PreparedStatement stUser = connect.prepareStatement(updateUser);

            stUser.setString(1, u.getName());
            stUser.setString(2, u.getLastname());
            stUser.setString(3, u.getEmail());
            stUser.setString(4, u.getPassword());
            stUser.executeUpdate();
            connect.close();
            System.out.println(u.getName() + "user has been updated correctly.");}
       
        catch (SQLException excep) {
            excep.printStackTrace();
            System.out.println("Error updating the user.");
        }
    }


    public void delete(User u) {
        try (Connection connect = DriverManager.getConnection(DB_URL+"DAOPizzaShop", USER, PASS);
        Statement stmn = connect.createStatement();){
            PreparedStatement stUser = connect.prepareStatement(deleteUser);

            stUser.setString(1, u.getEmail());
            stUser.executeUpdate();
            connect.close();
            System.out.println(u.getEmail() + "user has been deleted correctly.");

        }catch (Exception excep) {
            excep.printStackTrace();
            System.out.println("Error deleting the user.");
        }
    }


    public User get(UUID id)  {

        ArrayList<User> listaUser = new ArrayList<User>();
        try (Connection connect = DriverManager.getConnection(DB_URL+"DAOPizzaShop", USER, PASS);
        Statement stmn = connect.createStatement();){
            PreparedStatement selectUser = connect.prepareStatement(getOneUser);
            selectUser.setBytes(1, UuidAdapter.getBytesFromUUID(id));
            ResultSet result = selectUser.executeQuery();
            System.out.println(result);
            connect.close();
            while (result.next()) {
                listaUser.add(new User(result.getString(2), result.getString(3),  result.getString(4),  result.getString(5)));
            }
            System.out.println(listaUser.get(0).getName());
            return listaUser.get(0);
        } catch (SQLException excep) {
            excep.printStackTrace();
            System.out.println("Error selecting the user.");
            return listaUser.get(0);
        }
    }


    public User getAll()  {
        ArrayList<User> listaUser = new ArrayList<User>();
        try (Connection connect = DriverManager.getConnection(DB_URL+"DAOPizzaShop", USER, PASS);
        Statement stmn = connect.createStatement();){
            PreparedStatement selectIng = connect.prepareStatement(getAllUser);

            ResultSet result = selectIng.executeQuery();
            System.out.println(result);

            while (result.next()) {
                listaUser.add(new User(result.getString(2), result.getString(3),  result.getString(4),  result.getString(5)));
            }
            System.out.println(listaUser.get(0).getName());
            return listaUser.get(0);
        } catch (SQLException excep) {
            excep.printStackTrace();
            System.out.println("Error selecting the pizzas.");
            return listaUser.get(0);
        }
    }
}
