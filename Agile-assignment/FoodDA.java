package da;


import domain.Food;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.util.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FoodDA {

    private String host = "jdbc:derby://localhost:1527/AssignmentModule2";
    private String user = "chunseng0411";
    private String password = "chunseng0411";
    private String tableName = "Food";
    private Connection conn;
    private PreparedStatement stmt;
    
    public FoodDA(){
        createConnection();
    }
    
    private void createConnection() {
        try {
            conn = DriverManager.getConnection(host, user, password);
            System.out.println("***TRACE: Connection established.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void shutDown() {
        if (conn != null)
            try {
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public ArrayList<Food> getFoods(){
        
        ArrayList<Food> Foods = new ArrayList<Food>();
        
       
        
        Statement st;
        
        ResultSet rs;
        
        Food foods;
        
        try {
            
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM Food");
            
            while(rs.next()){
                
                foods = new Food(
                        rs.getString("ID"),
                        rs.getString("Name"),
                        rs.getString("Type"),
                        rs.getString("Description"),
                        rs.getDouble("Price")
                        
                );
                
                Foods.add(foods);
            }
             
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Fail to retrieve data from database!!!", "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
        }

        return Foods;
    }
/*
    public ArrayList<Food> getRecord(String ID) {
        ArrayList<Food> Foods = new ArrayList<Food>();
        
        String queryStr = "SELECT * FROM Food WHERE ID = ?";
        Food foods = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, ID);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                foods = new Food(rs.getString("ID"), rs.getString("Name"), rs.getString("Type"),rs.getString("Description"),rs.getDouble("Price"));
                Foods.add(foods);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return Foods;
    }
    */
    /*
    public Food selectedRow(String ID){
        String queryStr = "SELECT * FROM " + tableName + " WHERE ID = ?";
        Food foods = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, ID);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                foods = new Food(rs.getString("ID"), rs.getString("Name"), rs.getString("Type"),rs.getString("Description"),rs.getDouble("Price"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return foods;
    }
    */
    public void addFood(String ID, String name, String type,String description, double price){
        String queryStr = "INSERT INTO "+tableName+" VALUES (?,?,?,?,?)";
         
            ResultSet rs = null;
            try {
                stmt = conn.prepareStatement(queryStr);
                stmt.setString(1, ID);
                stmt.setString(2, name);
                stmt.setString(3, type);
                stmt.setString(4, description);
                stmt.setDouble(5, price);
           
                stmt.executeUpdate();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        
    }
    
    public void deleteFood(String ID){
        String queryStr = "DELETE FROM Food WHERE ID = ?";
         
            ResultSet rs = null;
            try {
                stmt = conn.prepareStatement(queryStr);
                stmt.setString(1, ID);
                
           
                stmt.executeUpdate();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        
    }
    
    public void editFood(String ID, String name, String type,String description, double price){
        String queryStr = "UPDATE Food SET Name = ?, Type = ?, Description=?, Price=? WHERE ID = ?";
         
            
            try {
                stmt = conn.prepareStatement(queryStr);
                stmt.setString(1, name);
                stmt.setString(2, type);
                stmt.setString(3, description);
                stmt.setDouble(4, price);
                stmt.setString(5, ID);
           
                stmt.executeUpdate();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        
    }
    
    
    public static void main(String[] args) {
        FoodDA da = new FoodDA();
    }
    
}
