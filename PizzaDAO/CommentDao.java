package PizzaDAO;

import java.sql.*;
import java.util.*;

import Domain.Comment;

public class CommentDao implements DaoW<Comment>, DaoR<Comment> {

    final String insertComment = """
                                    INSERT INTO comment(id, text, score, date, id_user, id_pizza) 
                                    VALUES (UUID_TO_BIN(?), ?, ?, ?, 
                                    (select id from user where user.name=?),
                                    (select id from pizza where pizza.name=?));
                                """;

    final String getAllComment = "SELECT id, text, score, date, id_user, id_pizza FROM comment";

    final String getOneComment = """
                                    SELECT id, text, score, date, id_user, id_pizza 
                                    FROM comment WHERE id = ?
                                 """;

    final String updateComment = """
                                    UPDATE comment SET text = ?, score = ?, date = ?
                                    WHERE id = UUID_TO_BIN(?)
                                 """;

    final String deleteComment = "DELETE FROM comment WHERE id = UUID_TO_BIN(?)";


    static final String DB_URL = System.getenv("DB_URL");
    static final String USER = System.getenv("user");
    static final String PASS = System.getenv("password");


    public void insert(Comment comm) {
        try (Connection connect = DriverManager.getConnection(DB_URL+"DAOPizzaShop", USER, PASS);
        Statement stmn = connect.createStatement();){
            PreparedStatement stComm = connect.prepareStatement(insertComment);

            stComm.setString(1, comm.getId().toString());
            stComm.setString(2, comm.getText());
            stComm.setFloat(3, comm.getScore());
            stComm.setString(4, comm.getDate().toString());

            stComm.executeUpdate();
            System.out.println("Comment has been saved correctly.");}
        catch (SQLException excep) {
            excep.printStackTrace();
            System.out.println("Error saving the comment.");
        }
    }


    public void update(Comment comm) {
        try (Connection connect = DriverManager.getConnection(DB_URL+"DAOPizzaShop", USER, PASS);
        Statement stmn = connect.createStatement();){
            PreparedStatement stComm = connect.prepareStatement(updateComment);


            stComm.setString(1, comm.getText());
            stComm.setFloat(2, comm.getScore());
            stComm.setString(3, comm.getDate().toString());
            stComm.setString(4, comm.getId().toString());

            stComm.executeUpdate();
            System.out.println("Comment has been updated correctly.");}
       
        catch (SQLException excep) {
            excep.printStackTrace();
            System.out.println("Error updating the comment.");
        }
    }


    public void delete(Comment comm) {
        try (Connection connect = DriverManager.getConnection(DB_URL+"DAOPizzaShop", USER, PASS);
        Statement stmn = connect.createStatement();){
            PreparedStatement stComm = connect.prepareStatement(deleteComment);

            stComm.setString(1, comm.getId().toString());
            stComm.executeUpdate();
            System.out.println("Comment has been deleted correctly.");

        }catch (Exception excep) {
            excep.printStackTrace();
            System.out.println("Error deleting the comment.");
        }
    }


    public ResultSet get(Comment comm) {
        ResultSet result=null;
        try (Connection connect = DriverManager.getConnection(DB_URL+"DAOPizzaShop", USER, PASS);
        Statement stmn = connect.createStatement();){
            PreparedStatement stComm = connect.prepareStatement(getOneComment);

            stComm.setString(1, comm.getId().toString());

            result=stComm.executeQuery();
            
            while (result.next()) {

                System.out.println(((ResultSet) result).getObject("id")+ "," 
                +((ResultSet) result).getString("text") + ", "
                +((ResultSet) result).getFloat("score") + ", "
                +((ResultSet) result).getString("date") + ", "
                +((ResultSet) result).getObject("id_user")+ ", "
                +((ResultSet) result).getObject("id_pizza")+" .");
                }

        } catch (SQLException excep) {
            excep.printStackTrace();
            System.out.println("Error selecting the user.");
        }
        return result;
    }
    public Comment get(UUID id)  {

        ArrayList<Comment> listaUser = new ArrayList<Comment>();
        try (Connection connect = DriverManager.getConnection(DB_URL+"DAOPizzaShop", USER, PASS);
        Statement stmn = connect.createStatement();){
            PreparedStatement selectUser = connect.prepareStatement(getOneComment);
            selectUser.setBytes(1, UuidAdapter.getBytesFromUUID(id));
            ResultSet result = selectUser.executeQuery();
            System.out.println(result);
            connect.close();
            while (result.next()) {
//                listaUser.add(new Comment(result.getString(2), result.getFloat(3),  result.getString(4), result.getObject(5), result.getObject(6)));
            }
            System.out.println(listaUser.get(0));
            return listaUser.get(0);
        } catch (SQLException excep) {
            excep.printStackTrace();
            System.out.println("Error selecting the user.");
            return listaUser.get(0);
        }
    }

    public Comment getAll() {
        ResultSet result=null;
        try (Connection connect = DriverManager.getConnection(DB_URL+"DAOPizzaShop", USER, PASS);
        Statement stmn = connect.createStatement();){
            PreparedStatement stPizza = connect.prepareStatement(getAllComment);
            result=stPizza.executeQuery();
        
            while (result.next()) {
            
                System.out.println(((ResultSet) result).getObject("id")+ "," 
                +((ResultSet) result).getString("text") + ", "
                +((ResultSet) result).getFloat("score") + ", "
                +((ResultSet) result).getString("date") + ", "
                +((ResultSet) result).getString("text") + ", "
                +((ResultSet) result).getObject("id_user")+ ", "
                +((ResultSet) result).getObject("id_pizza")+" .");
            }

        } catch (SQLException excep){
            excep.printStackTrace();
            System.out.println("Error selecting the user.");
        }
        return (Comment) result;
    }
}