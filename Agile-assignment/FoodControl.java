package control;

import da.FoodDA;
import domain.Food;
import java.util.*;

public class FoodControl {
    
    private FoodDA foodDA;

    public FoodControl() {
        foodDA = new FoodDA();
    }
    
    public ArrayList<Food> getAllRecord(){
        return foodDA.getFoods();
    }
    
/*
    public ArrayList<Food> selectRecord(String ID) {
        return foodDA.getRecord(ID);
    }
    */
   /* 
    public Food selectedData(String ID){
        return foodDA.selectedRow(ID);
    }
    */
     public void addData(String ID, String name, String type, String description, double price){
        foodDA.addFood(ID, name, type, description, price);
    }
     
    public void deleteData(String ID){
        foodDA.deleteFood(ID);
    }
    
    public void updateData(String ID, String name, String type, String description, double price){
        foodDA.editFood(ID, name, type, description, price);
    }
 
}
