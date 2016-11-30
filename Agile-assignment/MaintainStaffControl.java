// author : Goh Weng Yong
package control;

import domain.Staff;
import da.StaffDA;
import java.sql.Date;
import java.util.ArrayList;

public class MaintainStaffControl {
    private StaffDA staff;
    public MaintainStaffControl(){
        staff = new StaffDA();
    }
    public void retrieveRecord(String staffID){
        staff.retrieveRecord(staffID);
    }
    public void updateRecord(String staffID, String staffName, String ICNO, String address,String phoneNo, String homeNo, Date dateOfBirth, String gender){
        staff.updateRecord(staffID, staffName, ICNO, address, phoneNo, homeNo, dateOfBirth, gender);
    }
    public void deleteRecord(String staffID){
        staff.deleteRecord(staffID);
    }
    public void createRecord(String staffID, String staffName, String ICNO, String address,String phoneNo, String homeNo, Date dateOfBirth, String gender){
        staff.createRecord(staffID, staffName, ICNO, address, phoneNo, homeNo, dateOfBirth, gender);
    }
    public ArrayList<Staff> getAllRecord(){
        return staff.retrieveAll();
    }
        
}
