//author : Goh Weng Yong
package domain;

import java.sql.Date;

public class Staff {
    private String staffID;
    private String staffName;
    private String ICNO;
    private String address;
    private String phoneNo;
    private String homeNo;
    private Date dateOfBirth;
    private String gender;
    public Staff(String staffID, String staffName, String ICNO, String address,String phoneNo, String homeNo, Date dateOfBirth, String gender){
        this.staffID = staffID;
        this.staffName = staffName;
        this.ICNO = ICNO;
        this.address = address;
        this.phoneNo = phoneNo;
        this.homeNo = homeNo;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }
    public String getStaffID(){
        return staffID;
    }
    public String getStaffName(){
        return staffName;
    }
    public String getICNO(){
        return ICNO;
    }
    public String getAddress(){
        return address;
    }
    public String getPhoneNo(){
        return phoneNo;
    }
    public String getHomeNo(){
        return homeNo;
    }
    public Date getDateOfBirth(){
        return dateOfBirth;
    }
    public String getGender(){
        return gender;
    }
    public String toString(){
        return "";
    }
}
