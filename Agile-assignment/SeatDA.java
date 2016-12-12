package da;

/**
 *
 * @author Gee
 */

import domain.Seat;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.util.*;
public class SeatDA {
    
    private String host = "jdbc:derby://localhost:1527/AssignmentModule2";
    private String user = "chunseng0411";
    private String password = "chunseng0411";
    private String tableName = "Seat";
    private Connection conn;
    private PreparedStatement stmt;
    
    public SeatDA(){
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
    
    public ArrayList<Seat> getSeats(){
        
        ArrayList<Seat> Seats = new ArrayList<Seat>();
        
       
        
        Statement st;
        
        ResultSet rs;
        
        Seat seats;
        
        try {
            
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM Seat");
            
            while(rs.next()){
                
                seats = new Seat(
                        rs.getString("Name"),
                        rs.getString("Date"),
                        rs.getString("Time"),
                        rs.getInt("NoOfPeople"),
                        rs.getString("Status")
                        
                );
                
                Seats.add(seats);
            }
             
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Fail to retrieve data from database!!!", "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
        }

        return Seats;
    }
    
    public ArrayList<Seat> searchRecord(String name) {
        ArrayList<Seat> Seats = new ArrayList<Seat>();
        
        String queryStr = "SELECT * FROM Seat WHERE Name = ?";
        Seat seats = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                seats = new Seat(rs.getString("Name"), rs.getString("Date"), rs.getString("Time"),rs.getInt("NoOfPeople"),rs.getString("Status"));
                Seats.add(seats);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return Seats;
    }
    
    public void addSeat(String name, String date, String time, int noOfPeople, String status){
        String queryStr = "INSERT INTO "+tableName+" VALUES (?,?,?,?,?)";
         
            ResultSet rs = null;
            try {
                stmt = conn.prepareStatement(queryStr);
                stmt.setString(1, name);
                stmt.setString(2, date);
                stmt.setString(3, time);
                stmt.setInt(4, noOfPeople);
                stmt.setString(5, status);
           
                stmt.executeUpdate();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        
    }
    
    public void deleteSeat(String name){
        String queryStr = "DELETE FROM Seat WHERE Name = ?";
         
            ResultSet rs = null;
            try {
                stmt = conn.prepareStatement(queryStr);
                stmt.setString(1, name);
                
           
                stmt.executeUpdate();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        
    }
    
    public void editSeat(String name, String date, String time, int noOfPeople, String status){
        String queryStr = "UPDATE Seat SET Date = ?, Time = ?, NoOfPeople=?, Status=? WHERE Name = ?";
         
            try {
                stmt = conn.prepareStatement(queryStr);
                stmt.setString(1, date);
                stmt.setString(2, time);
                stmt.setInt(3, noOfPeople);
                stmt.setString(4, status);
                stmt.setString(5, name);
           
                stmt.executeUpdate();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
