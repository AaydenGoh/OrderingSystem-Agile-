// author : Hamish Then
package domain;

public class Order {
    private String orderID;
    private int orderCount;
    private String foodID;
    private String foodName;
    private int qty;
    private double price;
    public Order(String orderID,int orderCount,String foodID,String foodName,int qty,double price){
        this.orderID = orderID;
        this.orderCount = orderCount;
        this.foodID = foodID;
        this.foodName = foodName;
        this.qty = qty;
        this.price = price;
    }
    public String getOrderID(){
        return orderID;
    }
    public int getOrderCount(){
        return orderCount;
    }
    public String getFoodID(){
        return foodID;
    }
    public String getFoodName(){
        return foodName;
    }
    public int getQty(){
        return qty;
    }
    public double getPrice(){
        return price;
    }
}
