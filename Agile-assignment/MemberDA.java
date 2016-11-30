//author : Goh Weng Yong
package da;


import domain.*;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MemberDA {
    private String host = "jdbc:derby://localhost:1527/restaurantDB";
    private String user = "gwy";
    private String password = "12345";
    private String tableName = "Member";
    private Connection conn;
    private PreparedStatement stmt;
    private Statement st;
    public MemberDA(){
        createConnection();
    }
    public ArrayList<Member> retrieveAll(){
        ArrayList<Member> member = new ArrayList<Member>();
        Member mem = null;
        try{
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Member");
            
            while(rs.next()){
                mem = new Member(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getString(8),rs.getInt(9),rs.getDate(10));
                member.add(mem);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return member;
    }
    public Member retrieveRecord(String memberID){
        Member member = null;
        try{
            stmt = conn.prepareStatement("SELECT * FROM Member WHERE MemberID = ?");
            stmt.setString(1,memberID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){                
                member = new Member(memberID,rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getString(8),rs.getInt(9),rs.getDate(10));
            }         
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "This Member ID does not exist.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return member;
    }
    public void updateRecord(String memberID,String ICNO,String memberName,String address,String phoneNo,String homeNo,Date dateOfBirth,String gender,int loyalPoint,Date expiredDate){
        try{
            stmt = conn.prepareStatement("UPDATE Member SET Membername = ?,ICNO = ?,Address = ?,Phoneno = ?,Homeno = ?,DOB = ?,Gender = ?,Loyal_pt = ?,Expireddate = ? WHERE MemberID = ?");
            stmt.setString(1,memberName);
            stmt.setString(2,ICNO);
            stmt.setString(3,address);
            stmt.setString(4,phoneNo);
            stmt.setString(5,homeNo);
            stmt.setDate(6,dateOfBirth);
            stmt.setString(7,gender);
            stmt.setInt(8,loyalPoint);
            stmt.setDate(9,expiredDate);
            stmt.setString(10,memberID);           
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Record successful updated.","Message",JOptionPane.PLAIN_MESSAGE);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error occur.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void updateRenewal(String memberID,Date expiredDate){
        try{
            stmt = conn.prepareStatement("UPDATE Member Set Expireddate = ? WHERE MemberID = ?");
            stmt.setString(2,memberID);
            stmt.setDate(1,expiredDate);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Membership renewal successful.","Message",JOptionPane.PLAIN_MESSAGE);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error occur", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void deleteRecord(String memberID){
        try{
            stmt = conn.prepareStatement("DELETE FROM Member WHERE MemberID = ?");
            stmt.setString(1,memberID);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Record successful deleted.","Message",JOptionPane.PLAIN_MESSAGE);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "This Member ID does not exist.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void createRecord(String memberID,String ICNO,String memberName,String address,String phoneNo,String homeNo,Date dateOfBirth,String gender,int loyalPoint,Date expiredDate){
        try{
            stmt = conn.prepareStatement("INSERT INTO Member VALUES (?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(2,memberName);
            stmt.setString(3,ICNO);
            stmt.setString(4,address);
            stmt.setString(5,phoneNo);
            stmt.setString(6,homeNo);
            stmt.setDate(7,dateOfBirth);
            stmt.setString(8,gender);
            stmt.setInt(9,loyalPoint);
            stmt.setDate(10,expiredDate);
            stmt.setString(1,memberID);
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
        new MemberDA();
    }
}
