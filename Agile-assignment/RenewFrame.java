// author : Goh Weng Yong
package ui;

import control.MaintainMemberControl;
import domain.Member;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import javax.swing.border.*;

public class RenewFrame extends JFrame{
    private JLabel[] label = {new JLabel("MemberID"),
                              new JLabel("Name"),
                              new JLabel("IC no"),
                              new JLabel("Expired Date"),
                              new JLabel("Renew Date")};
    private JTextField[] input = new JTextField[label.length];
    private JButton[] but = {new JButton("Search"),
                             new JButton("OK"),
                             new JButton("Cancel")};
    private MaintainMemberControl mmc = new MaintainMemberControl();
    private String xdate;
    public RenewFrame(){
        JPanel[] panel = {new JPanel(new GridLayout(label.length,2)), new JPanel()};
        for(int i=0;i<label.length;i++){
            input[i] = new JTextField();
            panel[0].add(label[i]);
            panel[0].add(input[i]);
            if(i>0)
                input[i].setEditable(false);
        }
        panel[0].setBorder(new TitledBorder("Renewal Statement"));
        panel[1].add(but[0]);
        panel[1].add(but[1]);
        panel[1].add(but[2]);
        but[0].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String memberID = input[0].getText();
                Member member = mmc.retrieveRecord(memberID);
                input[1].setText(member.getMemberName());
                input[2].setText(member.getICNO());
                xdate = String.valueOf(member.getExpiredDate()).substring(4);
                input[3].setText(String.valueOf(member.getExpiredDate()));
                int year = Integer.valueOf(String.valueOf(member.getExpiredDate()).substring(0,4))+1;
                xdate = String.valueOf(year)+xdate;
                input[4].setText(xdate);
            }
        });
        but[1].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int xday = Integer.valueOf(xdate.substring(8));
                int xmonth = Integer.valueOf(xdate.substring(5,7))-1;
                int xyear = Integer.valueOf(xdate.substring(0,4))-1900;
                Date xDate = new Date(xyear,xmonth,xday);
                mmc.updateRenewal(input[0].getText(),xDate);
                
            }
        });
        but[2].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                close();
            }
        });
        add(panel[0],BorderLayout.CENTER);
        add(panel[1],BorderLayout.SOUTH);
        setTitle("Membership renewal");
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(400,350);
    }
    public void close(){

        WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOS­ING);
        Toolkit.getDefaultToolkit().getSystemEve­ntQueue().postEvent(winClosingEvent);

    }
    public static void main(String[] args){
        new RenewFrame();
    }
}
