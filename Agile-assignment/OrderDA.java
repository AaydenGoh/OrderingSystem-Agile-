package da;


import domain.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class OrderDA {

    //for database purpose
    private String host = "jdbc:derby://localhost:1527/AssignmentModule2";
    private String xhost = "jdbc:derby://localhost:1527/OrderDB";
    private String user = "chunseng0411";
    private String password = "chunseng0411";
    private String xuser = "Hamish";
    private String xpassword = "123";
    private String tableName1 = "FOOD";
    private String tableName2 = "SEAT";
    private Connection conn;  
    private Connection conn2;  
    private PreparedStatement stmt;
        
    public OrderDA(){
        createConnection();
    }
    
    
    
    public Food getRecord(String id){
        String query = "SELECT * FROM " +tableName1+ " WHERE ID = ?";
        Food food = null;
        try{               
            stmt = conn.prepareStatement(query);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            
                if(rs.next())
                    food = new Food (id, rs.getString("name"), rs.getString("type"), rs.getString("description"), Double.parseDouble(rs.getString("price")));
            
        }catch(SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return food;
    }
    
    public ArrayList<Order> searchRecord(int orderCount){
        String query = "SELECT * FROM ORDERLIST WHERE ordercount = ?";
        ArrayList<Order> orders=new ArrayList<Order>();
        Order order = null;
        try{               
            stmt = conn2.prepareStatement(query);
            stmt.setInt(1, orderCount);
            ResultSet rs = stmt.executeQuery();
            
                while(rs.next()){
                    order = new Order (rs.getString("OrderID"),rs.getInt("OrderCount"), rs.getString("ItemID"), rs.getString("ItemName"), rs.getInt("ItemQuantity"), rs.getDouble("Itemprice"));
                    orders.add(order);
                }
        }catch(SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return orders;
    }
    
    public int setOrderCount(){
        String query = "SELECT ordercount FROM ORDERLIST";
       
        int count = 0;
        try{               
            stmt = conn2.prepareStatement(query);
            
            ResultSet rs = stmt.executeQuery();
            
                while(rs.next()){
                    int countx=rs.getInt("ordercount");
                    if(countx>count){
                        count=countx;
                    }
                }
        }catch(SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return count;
    }
    public String setOrderID(){
        String query = "SELECT orderID FROM ORDERLIST";
       
        String count = "1000";
        try{               
            stmt = conn2.prepareStatement(query);
            
            ResultSet rs = stmt.executeQuery();
            
                while(rs.next()){
                    String countx = rs.getString(1);

                    if(Integer.valueOf(countx)>Integer.valueOf(count))
                        count = countx;
                    continue;
                }
        }catch(SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return count;
    }
    //!!
    public void setRecord(String orderID,int orderCount,String foodID,String foodName,int qty,double price){
        String queryStr = "INSERT INTO ORDERLIST VALUES (?,?,?,?,?,?)";
         
            ResultSet rs = null;
            try {
                stmt = conn2.prepareStatement(queryStr);
                stmt.setString(1, orderID);
                stmt.setInt(2, orderCount);
                stmt.setString(3, foodID);
                stmt.setString(4, foodName);
                stmt.setInt(5, qty);
                stmt.setDouble(6, price);
                stmt.executeUpdate();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        
    }
    
    /*
    public void editRecord(String orderID,int orderCount,String foodID,String foodName,int qty,double price){
        String queryStr = "UPDATE ORDERLIST SET ORDERCOUNT=?, ITEMID=?, ITEMNAME=?,FOODQUANTITY=?,PRICE=? WHERE ORDERID=?";
         
            
            try {
                stmt = conn.prepareStatement(queryStr);
                stmt.setInt(1, orderCount);
                stmt.setString(2, foodID);
                stmt.setString(3, foodName);
                stmt.setInt(4, qty);
                stmt.setDouble(5, price);
                stmt.setString(6, orderID);
                
                stmt.executeUpdate();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        
    }
    */
    //
    public void deleteRecord(String OrderID){
        String queryStr = "DELETE FROM ORDERLIST WHERE ORDERID = ?";
         
            ResultSet rs = null;
            try {
                stmt = conn2.prepareStatement(queryStr);
                stmt.setString(1, OrderID);
                
           
                stmt.executeUpdate();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        
    }
    
    private void createConnection() {
        try {
            conn = DriverManager.getConnection(host, user, password);
            conn2 = DriverManager.getConnection(xhost, xuser, xpassword);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private void shutDown(){
        if(conn != null){
            try{
                conn.close();
            } catch (SQLException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public static void main(String[] args) {
    }
    
    
}
