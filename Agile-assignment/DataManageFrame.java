// author : Goh Weng Yong
package ui;

import control.*;
import domain.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.border.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

public class DataManageFrame extends JFrame{
    private JTextField input = new JTextField();
    private JButton search = new JButton("Search");
    private JLabel[] member = {new JLabel("MemberID"),
                              new JLabel("Name"),
                              new JLabel("IC no"),
                              new JLabel("Date of birth"),
                              new JLabel("Address"),
                              new JLabel("Phone number"),
                              new JLabel("Home number"),
                              new JLabel("Gender"),
                              new JLabel("Loyalty Point"),
                              new JLabel("ExpiredDate")};
    private JLabel[] staff = {new JLabel("StaffID"),
                              new JLabel("Name"),
                              new JLabel("IC no"),
                              new JLabel("Date of birth"),
                              new JLabel("Address"),
                              new JLabel("Phone number"),
                              new JLabel("Home number"),
                              new JLabel("Gender")};
    private JPanel[] panel = {new JPanel(new GridLayout(3,2)),
                              new JPanel(),
                              new JPanel(),
                              new JPanel(),
                              new JPanel(new GridLayout(staff.length,2)),
                              new JPanel(new GridLayout(member.length,2)),
                              new JPanel(new BorderLayout()),
                              new JPanel()};
    private JButton[] but = {new JButton("Modify"),
                             new JButton("Save"),
                             new JButton("Delete")};
    private JTextField[] staffInput = new JTextField[staff.length];
    private JTextField[] memberInput = new JTextField[member.length];
    private String[] cols = new String[staff.length];
    private String[] col = new String[member.length];   
    private Object[] srowData = new Object[staff.length];
    private Object[] rowData = new Object[member.length];
    private JMenuBar menu = new JMenuBar();
    private JComboBox type = new JComboBox(new String[]{"Staff ID","Member ID"});
    private JTabbedPane tab = new JTabbedPane();
    private JTable staTable;
    private JTable memTable;
    private JScrollPane[] scrollPane = new JScrollPane[2];
    private static int a=1;
    private MaintainMemberControl b = new MaintainMemberControl();
    private MaintainStaffControl c = new MaintainStaffControl();
    private ArrayList<Member> mem = new ArrayList<Member>();
    private ArrayList<Staff> sta = new ArrayList<Staff>();
    private DefaultTableModel model = new DefaultTableModel();
    private DefaultTableModel model1 = new DefaultTableModel();
    public DataManageFrame(){
        
        panel[0].add(new JLabel("Search Keyword"));
        panel[0].add(input);
        panel[0].add(new JLabel("Search Type"));
        panel[0].add(type);
        
        panel[0].setBorder(new TitledBorder("Search Engine"));
        panel[4].setBorder(new TitledBorder("Staff Details"));
        panel[5].setBorder(new TitledBorder("Member Details"));
        for(int i=0;i<member.length;i++){
            memberInput[i] = new JTextField();
            memberInput[i].setEditable(false);
            panel[5].add(member[i]);
            panel[5].add(memberInput[i]);
            col[i] = member[i].getText();            
        }
            
        for(int i=0;i<staff.length;i++){
             staffInput[i] = new JTextField();
             staffInput[i].setEditable(false);
             panel[4].add(staff[i]);
             panel[4].add(staffInput[i]);
             cols[i] = staff[i].getText();
        }
        for(int i=0;i<but.length;i++)
            panel[7].add(but[i]);

        model1.setColumnIdentifiers(cols);
        model.setColumnIdentifiers(col);
        memTable = new JTable();     
        staTable = new JTable();    
        createStaffTable();
        createMemberTable();         

        staTable.setFillsViewportHeight(true);
        memTable.setFillsViewportHeight(true);
        
        memTable.addMouseListener(new MouseAdapter(){
           public void mouseClicked(MouseEvent e){
               int row = memTable.getSelectedRow();
               String[] rowSelected = new String[member.length];
               Date date = (Date) memTable.getModel().getValueAt(row,3);
               Date date1 = (Date) memTable.getModel().getValueAt(row,9);
               int loyalP = (int) memTable.getModel().getValueAt(row,8);
               for(int i=0;i<member.length;i++){
                    if(i==3||i==8||i==9)
                        continue;
                    rowSelected[i] = (String) memTable.getModel().getValueAt(row,i);
                    memberInput[i].setText(rowSelected[i]);
                }
               memberInput[3].setText(String.valueOf(date));
               memberInput[8].setText(String.valueOf(loyalP));
               memberInput[9].setText(String.valueOf(date1));
           } 
        });
        staTable.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                int row = staTable.getSelectedRow();
                String[] rowSelected = new String[staff.length];
                Date date = (Date) staTable.getModel().getValueAt(row,3);
                for(int i=0;i<staff.length;i++){
                    if(i==3)
                        continue;
                    rowSelected[i] = (String) staTable.getModel().getValueAt(row,i);
                    staffInput[i].setText(rowSelected[i]);
                }               
                staffInput[3].setText(String.valueOf(date));
            }
        });
        
        
        scrollPane[0] = new JScrollPane(staTable);
        scrollPane[1] = new JScrollPane(memTable);
        panel[1].add(scrollPane[0]);
        panel[2].add(scrollPane[1]);
        tab.addTab("Staff",panel[1]);
        tab.addTab("Member",panel[2]);
        tab.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e) {
                if(tab.getSelectedIndex()==1){
                    panel[6].remove(panel[4]);
                    panel[6].add(panel[5],BorderLayout.CENTER);
                    for(int i=0;i<member.length;i++){
                         memberInput[i].setText("");
                         memberInput[i].setEditable(false);
                    }    
                }else{
                    panel[6].remove(panel[5]);
                    panel[6].add(panel[4],BorderLayout.CENTER);
                    for(int i=0;i<staff.length;i++){
                        staffInput[i].setText("");
                        staffInput[i].setEditable(false);
                    }           
                }               
            }
        });
        panel[3].add(tab);
        
        
        
        JMenu option = new JMenu("Option");
        JMenu file = new JMenu("Create new");
        JMenuItem renew = new JMenuItem("Renewal");
        JMenuItem[] staMem = {new JMenuItem("Staff"), new JMenuItem("Member")};
        menu.add(option);
        option.add(file);
        option.addSeparator();
        option.add(renew);
        file.add(staMem[0]);
        file.add(staMem[1]);       
        setJMenuBar(menu);
        
        but[0].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){               
                boolean ans;
                if(a%2==0)
                    ans = false;
                else 
                    ans = true;
                if(tab.getSelectedIndex()==1){   
                    for(int i=0;i<member.length;i++)
                        memberInput[i].setEditable(ans);
                }else{
                    for(int i=0;i<staff.length;i++)
                        staffInput[i].setEditable(ans);
                }
                a++;
            }
        });
        but[1].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String[] newInput;
                if(tab.getSelectedIndex()==1){   
                    newInput = new String[member.length];
                    for(int i=0;i<member.length;i++){
                        newInput[i] = memberInput[i].getText();
                    }
                    int day = Integer.valueOf(newInput[3].substring(8));
                    int month = Integer.valueOf(newInput[3].substring(5,7))-1;
                    int year = Integer.valueOf(newInput[3].substring(0,4))-1900;
                    Date date = new Date(year,month,day);
                    
                    int xday = Integer.valueOf(newInput[9].substring(8));
                    int xmonth = Integer.valueOf(newInput[9].substring(5,7))-1;
                    int xyear = Integer.valueOf(newInput[9].substring(0,4))-1900;
                    Date xdate = new Date(xyear,xmonth,xday);
                    
                    b.updateRecord(newInput[0], newInput[1],newInput[2],newInput[4],newInput[5],newInput[6], date, newInput[7], Integer.valueOf(newInput[8]),xdate);
                    memTable.removeAll();
                    createMemberTable();
                    for(int i=0;i<member.length;i++)
                        memberInput[i].setEditable(false);
                    a++;
                }else{
                    newInput = new String[staff.length];
                    for(int i=0;i<staff.length;i++){
                        newInput[i] = staffInput[i].getText();
                    }
                    int day = Integer.valueOf(newInput[3].substring(8));
                    int month = Integer.valueOf(newInput[3].substring(5,7))-1;
                    int year = Integer.valueOf(newInput[3].substring(0,4))-1900;
                    Date date = new Date(year,month,day);
                    
                    c.updateRecord(newInput[0], newInput[1], newInput[2], newInput[4], newInput[5],newInput[6], date, newInput[7]);
                    staTable.removeAll();
                    createStaffTable();
                    for(int i=0;i<staff.length;i++)
                        staffInput[i].setEditable(false);
                    a++;
                }
            }
        });
        but[2].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(tab.getSelectedIndex()==1){   
                    String memberID = memberInput[0].getText();
                    b.deleteRecord(memberID);
                    for(int i=0;i<member.length;i++)
                        memberInput[i].setText("");
                    memTable.removeAll();
                    createMemberTable();
                }else{
                    String staffID = staffInput[0].getText();
                    c.deleteRecord(staffID);
                    for(int i=0;i<staff.length;i++)
                        staffInput[i].setText("");
                    staTable.removeAll();
                    createStaffTable();
                }
            }
        });
        staMem[0].addActionListener(new staffListener());
        staMem[1].addActionListener(new memberListener());
        renew.addActionListener(new renewListener());
        input.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent e){
                if(tab.getSelectedIndex()==1){   
                    String inputSearch = input.getText();
                
                DefaultTableModel searchTable = new DefaultTableModel();
                searchTable.setColumnIdentifiers(col);
                //ArrayList<Member> mem = new ArrayList<Member>();
                mem = b.getAllRecord();
                
                for(int i=0;i<mem.size();i++){
                    rowData[0] = mem.get(i).getMemberID();
                    rowData[1] = mem.get(i).getMemberName();
                    rowData[2] = mem.get(i).getICNO();
                    rowData[3] = mem.get(i).getDateOfBirth();
                    rowData[4] = mem.get(i).getAddress();
                    rowData[5] = mem.get(i).getPhoneNo();
                    rowData[6] = mem.get(i).getHomeNo();
                    rowData[7] = mem.get(i).getGender();
                    rowData[8] = mem.get(i).getLoyalPoint();
                    rowData[9] = mem.get(i).getExpiredDate();   
                    
                    if(mem.get(i).getMemberID().contains(input.getText()))
                        searchTable.addRow(rowData);
                    if(input.getText().equals(""))
                        memTable.setModel(model);
                }
                memTable.setModel(searchTable);
                }else{
                    DefaultTableModel searchTable = new DefaultTableModel();
                    searchTable.setColumnIdentifiers(cols);
                    //ArrayList<Staff>  = new ArrayList<Staff>();
                    sta = c.getAllRecord();
                    
                    for(int i=0;i<sta.size();i++){
                        rowData[0] = sta.get(i).getStaffID();
                        rowData[1] = sta.get(i).getStaffName();
                        rowData[2] = sta.get(i).getICNO();
                        rowData[3] = sta.get(i).getDateOfBirth();
                        rowData[4] = sta.get(i).getAddress();
                        rowData[5] = sta.get(i).getPhoneNo();
                        rowData[6] = sta.get(i).getHomeNo();
                        rowData[7] = sta.get(i).getGender();
                        
                        if(sta.get(i).getStaffID().contains(input.getText()))
                            searchTable.addRow(rowData);
                    }
                    
                    staTable.setModel(searchTable);
                    if(input.getText().equals(""))
                        staTable.setModel(model1);
                }
                
            }
        });
        
        panel[6].add(panel[4],BorderLayout.CENTER);
        panel[6].add(panel[0],BorderLayout.NORTH);
        panel[6].add(panel[7],BorderLayout.SOUTH);
        add(panel[6],BorderLayout.EAST);
        add(panel[3],BorderLayout.CENTER);
        
        
        
        setTitle("Data Manage");
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        //setSize(400,400);
        pack();
    }
    private void createStaffTable(){
        sta = c.getAllRecord();
        model1.setRowCount(0);
        
        for(int i=0;i<sta.size();i++){
            rowData[0] = sta.get(i).getStaffID();
            rowData[1] = sta.get(i).getStaffName();
            rowData[2] = sta.get(i).getICNO();
            rowData[3] = sta.get(i).getDateOfBirth();
            rowData[4] = sta.get(i).getAddress();
            rowData[5] = sta.get(i).getPhoneNo();
            rowData[6] = sta.get(i).getHomeNo();
            rowData[7] = sta.get(i).getGender();

            model1.addRow(rowData);
            }  
        staTable.setModel(model1);
    }
    private void createMemberTable(){
        mem = b.getAllRecord();
        model.setRowCount(0);
        
        for(int i=0;i<mem.size();i++){
            rowData[0] = mem.get(i).getMemberID();
            rowData[1] = mem.get(i).getMemberName();
            rowData[2] = mem.get(i).getICNO();
            rowData[3] = mem.get(i).getDateOfBirth();
            rowData[4] = mem.get(i).getAddress();
            rowData[5] = mem.get(i).getPhoneNo();
            rowData[6] = mem.get(i).getHomeNo();
            rowData[7] = mem.get(i).getGender();
            rowData[8] = mem.get(i).getLoyalPoint();
            rowData[9] = mem.get(i).getExpiredDate();
     
            model.addRow(rowData);
        }
        memTable.setModel(model);
    }
    
    private class memberListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            new CreateMemberFrame();
            if(tab.getSelectedIndex()==1){
                memTable.removeAll();
                createMemberTable();
            }
           
        }
    }
    private class staffListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            new CreateStaffFrame();
            if(tab.getSelectedIndex()==0){
                staTable.removeAll();
                createStaffTable();
            }     
        }
    }
    private class renewListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            new RenewFrame();
        }
    }
    
    public static void main(String[] args){
        new DataManageFrame();
    }
}
