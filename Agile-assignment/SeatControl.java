/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

/**
 *
 * @author Gee
 */

import da.SeatDA;
import domain.Seat;
import java.util.*;

public class SeatControl {
    private SeatDA seatDA;
    
    public SeatControl() {
        seatDA = new SeatDA();
    }
    
    public ArrayList<Seat> getAllRecord(){
        return seatDA.getSeats();
    }
    
    public ArrayList<Seat> getSearch(String name){
        return seatDA.searchRecord(name);
    }
    
    public void addRecord(String name, String date, String time, int noOfPeople, String status){
        seatDA.addSeat(name, date, time, noOfPeople, status);
    }
    
    public void deleteData(String name){
        seatDA.deleteSeat(name);
    }
    
    public void updateData(String name, String date, String time, int noOfPeople, String status){
        seatDA.editSeat(name, date, time, noOfPeople, status);
    }
    
}
