package da;

import domain.Order;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

public class PaymentDA {
    private String host = "jdbc:derby://localhost:1527/OrderDB";
    private String user = "Hamish";
    private String password = "123";
    private String tableName = "OrderList";
    private Connection conn;
    private PreparedStatement stmt;
    
    public PaymentDA() {
        createConnection();
    }
    
    
    public ArrayList<Order> getBill(int orderCount) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE ordercount = ?";
         ArrayList<Order> order = new ArrayList<Order>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setInt(1, orderCount);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                order.add(new Order(rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getDouble(6)));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return order;
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
    
    public static void main(String[] args) {
        PaymentDA da = new PaymentDA();
        //Programme programme = da.getRecord("IA");
        //System.out.println(programme);
    }
}
