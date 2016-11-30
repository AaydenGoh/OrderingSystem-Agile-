//author : Goh Weng Yong
package domain;

import java.sql.Date;

public class Member {
    private String memberID;
    private String memberName;
    private String ICNO;
    private String address;
    private String phoneNo;
    private String homeNo;
    private Date dateOfBirth;
    private String gender;
    private int loyalPoint;
    private Date expiredDate;
    
    public Member(String memberID,
                  String ICNO,
                  String memberName,
                  String address,
                  String phoneNo,
                  String homeNo,
                  Date dateOfBirth,
                  String gender,
                  int loyalPoint,
                  Date expiredDate){
        this.memberID = memberID;
        this.ICNO = ICNO;
        this.memberName = memberName;
        this.address = address;
        this.phoneNo = phoneNo;
        this.homeNo = homeNo;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.loyalPoint = loyalPoint;
        this.expiredDate = expiredDate;
        
    }
    public String getMemberID(){
        return memberID;
    }
    public String getICNO(){
        return ICNO;
    }
    public String getMemberName(){
        return memberName;
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
    public String getGender(){
        return gender;
    }
    public Date getDateOfBirth(){
        return dateOfBirth;
    }
    public int getLoyalPoint(){
        return loyalPoint;
    }
    public Date getExpiredDate(){
        return expiredDate;
    }
    public String toString(){
        return "";
    }
}
