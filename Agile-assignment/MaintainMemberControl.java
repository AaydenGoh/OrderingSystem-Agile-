// author : Goh Weng Yong
package control;

import da.MemberDA;
import domain.Member;
import java.sql.Date;
import java.util.ArrayList;

public class MaintainMemberControl {
    private MemberDA member;
    public MaintainMemberControl(){
        member = new MemberDA();
    }
    public Member retrieveRecord(String memberID){
        return member.retrieveRecord(memberID);
    }
    public void updateRecord(String memberID,String ICNO,String memberName,String address,String phoneNo,String homeNo,Date dateOfBirth,String gender,int loyalPoint,Date expiredDate){
        member.updateRecord(memberID, ICNO, memberName, address, phoneNo, homeNo, dateOfBirth, gender,loyalPoint,expiredDate);
    }
    public void deleteRecord(String memberID){
        member.deleteRecord(memberID);
    }
    public void createRecord(String memberID,String ICNO,String memberName,String address,String phoneNo,String homeNo,Date dateOfBirth,String gender,int loyalPoint,Date expiredDate){
        member.createRecord(memberID, ICNO, memberName, address, phoneNo, homeNo, dateOfBirth, gender,loyalPoint,expiredDate);
    }
    public ArrayList<Member> getAllRecord(){
        return member.retrieveAll();
    }
    public void updateRenewal(String memberID,Date expiredDate){
        member.updateRenewal(memberID, expiredDate);
    }
}
