
package control;

import da.OrderDA;
import domain.Food;
import domain.Order;
import domain.Seat;
import java.util.ArrayList;

public class OrderControl {
    private OrderDA odrDA;
    
    public OrderControl(){
        odrDA = new OrderDA();
    }
    
    public Food selectRecord(String id){
        return odrDA.getRecord(id);
    }
    
    public ArrayList<Order> searchRecord(int orderCount){
        return odrDA.searchRecord(orderCount);
    }
    
    public void createRecord(String orderID,int orderCount,String foodID,String foodName,int qty,double price){
        odrDA.setRecord(orderID, orderCount, foodID, foodName, qty, price);
    }
    
   
    
    public void deleteRecord(String orderID){
        odrDA.deleteRecord(orderID);
    }
    
    public int getOrderCount(){
         return odrDA.setOrderCount();
    }
     public String getOrderID(){
         return odrDA.setOrderID();
    }
}
