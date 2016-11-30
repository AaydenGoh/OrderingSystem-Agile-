//author : Goh Weng Yong
package da;

import java.sql.*;
import domain.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import domain.*;

public class StaffDA {
    private String host = "jdbc:derby://localhost:1527/restaurantDB";
    private String user = "gwy";
    private String password = "12345";
    private String tableName = "Staff";
    private Connection conn;
    private PreparedStatement stmt;
    private Statement st;
    public StaffDA(){
        createConnection();
    }
    public ArrayList<Staff> retrieveAll(){
        ArrayList<Staff> staff = new ArrayList<Staff>();
        Staff sta = null;
        try{
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Staff");
            
            while(rs.next()){
                sta = new Staff(rs.getString(1),rs.getString(3),rs.getString(2),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getString(8));
                staff.add(sta);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return staff;
    }
    public Staff retrieveRecord(String staffID){
        Staff staff = null;
        try{
            stmt = conn.prepareStatement("SELECT * FROM Staff Where STAFFID = ?");
            stmt.setString(1,staffID);
            ResultSet rs = stmt.executeQuery();
            staff = new Staff(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getString(8));
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "This Staff ID does not exist.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return staff;
    }
    public void updateRecord(String staffID, String staffName, String ICNO, String address,String phoneNo, String homeNo, Date dateOfBirth, String gender){
        try{
            stmt = conn.prepareStatement("UPDATE Staff SET Staffname = ?,ICNO = ?,Address = ?,Phoneno = ?,Homeno = ?,DOB = ?,Gender = ? WHERE StaffID = ?");
            stmt.setString(2,staffName);
            stmt.setString(1,ICNO);
            stmt.setString(3,address);
            stmt.setString(4,phoneNo);
            stmt.setString(5,homeNo);
            stmt.setDate(6,dateOfBirth);
            stmt.setString(7,gender);
            stmt.setString(8,staffID);
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Record successful updated.","Message",JOptionPane.PLAIN_MESSAGE);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error occur.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void deleteRecord(String staffID){
        try{
            stmt = conn.prepareStatement("DELETE FROM Staff WHERE StaffID = ?");
            stmt.setString(1,staffID);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Record successful deleted.","Message",JOptionPane.PLAIN_MESSAGE);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "This Staff ID does not exist.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    public void createRecord(String staffID, String staffName, String ICNO, String address,String phoneNo, String homeNo, Date dateOfBirth, String gender){
        try{
            stmt = conn.prepareStatement("INSERT INTO Staff VALUES (?,?,?,?,?,?,?,?)");
            stmt.setString(2,staffName);
            stmt.setString(3,ICNO);
            stmt.setString(4,address);
            stmt.setString(5,phoneNo);
            stmt.setString(6,homeNo);
            stmt.setDate(7,dateOfBirth);
            stmt.setString(8,gender);
            stmt.setString(1,staffID);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Record successful created.","Message",JOptionPane.PLAIN_MESSAGE);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error occur.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void createConnection(){
        try {
            conn = DriverManager.getConnection(host, user, password);
            System.out.println("***TRACE: Connection established.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args){
        new StaffDA();
    }
}
