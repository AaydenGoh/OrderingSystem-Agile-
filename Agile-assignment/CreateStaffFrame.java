// author : Goh Weng Yong
package ui;

import control.MaintainStaffControl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import javax.swing.border.*;

public class CreateStaffFrame extends JFrame{
    private JLabel[] label = {new JLabel("StaffID"),
                              new JLabel("Name"),
                              new JLabel("IC no"),
                              new JLabel("Date of birth"),
                              new JLabel("Address"),
                              new JLabel("Phone number"),
                              new JLabel("Home number"),
                              new JLabel("Gender")};
    private JTextField[] input = new JTextField[label.length];
    private JButton[] but = {new JButton("OK"),
                             new JButton("Cancel")};
    public CreateStaffFrame(){
        JPanel panel = new JPanel(new GridLayout(label.length,2));
        JPanel panel1 = new JPanel();

            
        for(int i=0;i<label.length;i++){
            input[i] = new JTextField();
            panel.add(label[i]);            
            panel.add(input[i]);
        }
 
        panel1.add(but[0]);
        panel1.add(but[1]);
        panel.setBorder(new TitledBorder("Staff Form"));
        
        but[0].addActionListener(new okListener());
        but[1].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                close();
            }
        });
        add(panel,BorderLayout.CENTER);
        add(panel1,BorderLayout.SOUTH);
        setTitle("Create new staff");
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(400,350);
    }
    private class okListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            MaintainStaffControl msc = new MaintainStaffControl();
            int year = Integer.valueOf(input[3].getText().substring(0,4))-1990;
            int mon = Integer.valueOf(input[3].getText().substring(5,7))-1;
            int day = Integer.valueOf(input[3].getText().substring(8));
            Date date = new Date(year,mon,day);  
            
            String id = input[0].getText();
            String name= input[1].getText();
            String ic= input[2].getText();
            String address= input[4].getText();
            String hp= input[5].getText();
            String home= input[6].getText();
            String gender= input[7].getText();
            
            msc.createRecord(id, name, ic, address, hp, home, date, gender);
        }
    }
    public void close(){

        WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOS­ING);
        Toolkit.getDefaultToolkit().getSystemEve­ntQueue().postEvent(winClosingEvent);

    }
    public static void main(String[] args){
        new CreateStaffFrame();
    }
}
