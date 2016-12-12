package ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import da.*;
import control.*;
import domain.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


public class ReadFoodMenu extends JFrame{
    // Food tab
    private JFrame addFoodFrame=new JFrame();
    
    private String [] foodTypeList={"All","Meat","SeaFood","Pasta","Rice","Drink"};
    private String [] foodTypeList2={"Meat","SeaFood","Pasta","Rice","Drink"};
    private String [] heading = {"ID", "Name", "Type", "Description","Price"};
    private String [][] foodList={};
    
    private JLabel jlbSearch=new JLabel("Search");
    private JTextField jtfSearch=new JTextField();
    //private JLabel jlbFilter=new JLabel("Filter");
    //private JComboBox jcbFilter=new JComboBox(foodTypeList);
    private JButton jbtAdd=new JButton("Add");
    private JButton jbtEdit=new JButton("Edit");
    private JButton jbtDelete=new JButton("Delete");
    private JButton jbtSave=new JButton("Save");
    private JButton jbtRefresh=new JButton("Refresh");
    
    private JTable table=new JTable();
    private DefaultTableModel model = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column)
            {
                return false;//This make the Jtable be non editable
            }
        };
  //  private JScrollPane scrollPane = new JScrollPane(table);
    
    //edit field======================================================    
    private JLabel jlbNewProduct=new JLabel("New Product");
    private JLabel jlbFoodName=new JLabel("Food Name");
    private JLabel jlbFoodId=new JLabel("Food ID");
    private JLabel jlbFoodType=new JLabel("Food Type");
    private JLabel jlbFoodDescription=new JLabel("Food Description");
    private JLabel jlbFoodPrice=new JLabel("Food Price");
    
    private JTextField jtfFoodName=new JTextField();
    private JTextField jtfFoodId=new JTextField();
    private JTextField jtfFoodDescription=new JTextField();
    private JTextField jtfFoodPrice=new JTextField();
    private JComboBox jcbFoodType=new JComboBox(foodTypeList2);
    
    private FoodControl foodControl= new FoodControl();
    
    //Seat tab=========================================================================================
    
    
    
    
    private String [] heading2 = {"Name","Date", "Time", "No Of People", "Status" };
    private String [] number = {"1","2","3","4","5","6","7","8","9","10","11","12"};
    private String [] reserve = {"All","Cancelled", "Reserved","Waiting"};
    private String [] reserve2 = {"Cancelled", "Reserved","Waiting"};
    
    private JLabel jlbName=new JLabel("Name");
    //private JLabel jlbTime=new JLabel("Time");
    //private JLabel jlbNoOfPeople=new JLabel("Number Of People");
    //private JLabel jlbStatus=new JLabel("Status");
    
    private JTextField jtfName=new JTextField();
    //private JTextField jtfTime=new JTextField();
    //private JComboBox jcbNoOfPeople=new JComboBox(number);
    //private JComboBox jcbStatus=new JComboBox(reserve);
    
    //edit field
    private JLabel jlbeName=new JLabel("Name");
    private JLabel jlbeDate=new JLabel("Date");
    private JLabel jlbeTime=new JLabel("Time");
    private JLabel jlbeNoOfPeople=new JLabel("Number Of People");
    private JLabel jlbeStatus=new JLabel("Status");
    
    private JTextField jtfeName=new JTextField();
    private JTextField jtfeDate=new JTextField();
    private JTextField jtfeTime=new JTextField();
    private JComboBox jcbeNoOfPeople=new JComboBox(number);
    private JComboBox jcbeStatus=new JComboBox(reserve2);
    
    private JTable table2=new JTable();
    private JScrollPane scrollPane2 = new JScrollPane(table2);
    
    private JButton jbtSearch=new JButton("Search");
    
    private JButton jbtSeatRefresh=new JButton("Refresh");
    private JButton jbtSeatAdd=new JButton("Add");
    private JButton jbtSeatDelete=new JButton("Delete");
    private JButton jbtSeatSave=new JButton("Save");
    private JButton jbtSeatEdit=new JButton("Edit");
    
    private SeatControl seatControl=new SeatControl();
    
    //main class
    public ReadFoodMenu(){
        
        //Food
        JPanel panel1=new JPanel();
        JPanel panel2=new JPanel();
        JPanel panel3=new JPanel();
        JPanel panel4=new JPanel();
        JPanel panel5=new JPanel();
        JPanel panel6=new JPanel();
        JTable table=new JTable();
        
        
        
        JTabbedPane tab1=new JTabbedPane();
        
       
        
        panel1.add(jlbSearch);
        panel1.add(jtfSearch);
        //panel1.add(jlbFilter);
        
        panel1.add(jbtAdd);
        panel1.add(jbtEdit);
        panel1.add(jbtDelete);
        jbtSave.setEnabled(false);
        panel1.add(jbtSave);
        panel1.add(jbtRefresh);
        panel1.setLayout(new GridLayout(1,7,10,10));
        panel1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        
        //Display table contain
        model.setColumnIdentifiers(heading);
        
        FoodControl displayFoods=new FoodControl();
        
        ArrayList<Food> foodList = new ArrayList<Food>();
        foodList = displayFoods.getAllRecord();
        
        Object[] rowData = new Object[5];
        
        for(int i = 0; i < foodList.size(); i++){
            
            rowData[0] = foodList.get(i).getID();
            rowData[1] = foodList.get(i).getName();
            rowData[2] = foodList.get(i).getType();
            rowData[3] = foodList.get(i).getDescription();
            rowData[4] = foodList.get(i).getPrice();
            
            model.addRow(rowData);
        }
        
        
        
        
        
        table.setModel(model);
        
        
        JScrollPane scrollPane = new JScrollPane(table);
        
        table.setFillsViewportHeight(false);
        panel2.add(scrollPane);
        //set column size
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(1).setPreferredWidth(130);
        table.getColumnModel().getColumn(2).setPreferredWidth(60);
        table.getColumnModel().getColumn(3).setPreferredWidth(170);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);
        //====================================================================

        
        
            
     
        


        
        //edit field
        panel3.add(jlbFoodName);
        jtfFoodName.setEditable(false);
        panel3.add(jtfFoodName);
        
        panel3.add(jlbFoodId);
        jtfFoodId.setEditable(false);
        panel3.add(jtfFoodId);
        
        panel3.add(jlbFoodType);
        jcbFoodType.setEditable(false);
        panel3.add(jcbFoodType);
        
        panel3.add(jlbFoodDescription);
        jtfFoodDescription.setEditable(false);
        panel3.add(jtfFoodDescription);
        
        panel3.add(jlbFoodPrice);
        jtfFoodPrice.setEditable(false);
        panel3.add(jtfFoodPrice);
        panel3.setLayout(new GridLayout(3,2,10,5));
        panel3.setPreferredSize(new Dimension(800,100));
        
        panel4.add(panel3, BorderLayout.CENTER);
        panel4.setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
        panel5.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel5.add(panel4);
        
        panel6.add(panel1, BorderLayout.NORTH);
        panel6.add(panel2, BorderLayout.CENTER);
        panel6.add(panel5, BorderLayout.SOUTH);
        panel6.setBorder(BorderFactory.createLineBorder(Color.black, 3, rootPaneCheckingEnabled));
        tab1.addTab("Food", panel6);
        
        //seat 
        //===========================================================================================================
        JPanel panel11=new JPanel();
        JPanel panel12=new JPanel();
        JPanel panel13=new JPanel();
        JPanel panel14=new JPanel();
        JPanel panel15=new JPanel();
        
        
        JPanel panel16=new JPanel();
        //Display seat Table==========================================================
        table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        DefaultTableModel model3 = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column)
            {
                return false;//This make the Jtable be non editable
            }
        };
       
        model3.setColumnIdentifiers(heading2);
        
        SeatControl displaySeats=new SeatControl();
        
        ArrayList<Seat> seatList = new ArrayList<Seat>();
        seatList = displaySeats.getAllRecord();
        
        Object[] seatData = new Object[5];
        
        for(int i = 0; i < seatList.size(); i++){
            
            seatData[0] = seatList.get(i).getName();
            seatData[1] = seatList.get(i).getDate();
            seatData[2] = seatList.get(i).getTime();
            seatData[3] = seatList.get(i).getNoOfPeople();
            seatData[4] = seatList.get(i).getStatus();
            
            model3.addRow(seatData);
        }
        
    
        table2.setModel(model3);
        JScrollPane scrollPane2 = new JScrollPane(table2);
        
        table2.setFillsViewportHeight(false);
        //set column size
        table2.getColumnModel().getColumn(0).setPreferredWidth(150);
        table2.getColumnModel().getColumn(1).setPreferredWidth(80);
        table2.getColumnModel().getColumn(2).setPreferredWidth(40);
        table2.getColumnModel().getColumn(3).setPreferredWidth(90);
        table2.getColumnModel().getColumn(4).setPreferredWidth(90);
        
        //======================================================================
        
        panel11.add(scrollPane2);
        panel11.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 5));
        
        panel12.add(jlbName);
        panel12.add(jtfName);
        
        
        //jbtSearch.setPreferredSize(new Dimension(100,30));
        //panel13.add(jbtSearch);
        //panel13.setLayout(new FlowLayout(FlowLayout.RIGHT));
        //panel12.add(panel14);
        //panel12.add(panel13);
        
        
        panel12.setLayout(new GridLayout(1,2,5,50));
        panel12.setPreferredSize(new Dimension(300,100));
        panel12.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "Enter the Search Detail"));
        
        
        panel15.add(jlbeName);
        jtfeName.setEditable(false);
        panel15.add(jtfeName);
        panel15.add(jlbeDate);
        jtfeDate.setEditable(false);
        panel15.add(jtfeDate);
        panel15.add(jlbeTime);
        jtfeTime.setEditable(false);
        panel15.add(jtfeTime);
        panel15.add(jlbeNoOfPeople);
        jcbeNoOfPeople.setEditable(false);
        panel15.add(jcbeNoOfPeople);
        panel15.add(jlbeStatus);
        jcbeStatus.setEditable(false);
        panel15.add(jcbeStatus);
        panel15.add(panel13);
        panel15.add(jbtSeatRefresh);
        panel15.add(jbtSeatAdd);
        panel15.add(jbtSeatEdit);
        jbtSeatSave.setEnabled(false);
        panel15.add(jbtSeatSave);
        panel15.add(jbtSeatDelete);
        panel15.setLayout(new GridLayout(4,8,10,5));
        panel15.setPreferredSize(new Dimension(750,150));
        panel15.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "Update Field"));
        
        panel16.add(panel11, BorderLayout.CENTER);
        panel16.add(panel12, BorderLayout.EAST);
        panel16.add(panel15, BorderLayout.SOUTH);
        
        panel16.setBorder(BorderFactory.createLineBorder(Color.black, 3, rootPaneCheckingEnabled));
        tab1.addTab("Seat", panel16);
      
        add(tab1);
        
        //listener event for Food==================================================================
        //jbtAdd.addActionListener(new addListenerClass());
        jbtEdit.addActionListener(new editListenerClass());
        jbtSave.addActionListener(new saveListenerClass());
        
        jtfSearch.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent e){
                String search=jtfSearch.getText();
                
                DefaultTableModel model2 = new DefaultTableModel();
                model2.setColumnIdentifiers(heading);
                FoodControl searchFoods=new FoodControl();
        
                ArrayList<Food> foodList2 = new ArrayList<Food>();
                foodList2 = searchFoods.getAllRecord();
        
                Object[] rowData2 = new Object[5];
        
                for(int i = 0; i < foodList2.size(); i++){
            
                    rowData2[0] = foodList2.get(i).getID();
                    rowData2[1] = foodList2.get(i).getName();
                    rowData2[2] = foodList2.get(i).getType();
                    rowData2[3] = foodList2.get(i).getDescription();
                    rowData2[4] = foodList2.get(i).getPrice();
                
                
                    if(foodList2.get(i).getID().contains(jtfSearch.getText())){
                        model2.addRow(rowData2);
                    }
                    
                }
        
                table.setModel(model2);
                
                table.getColumnModel().getColumn(0).setPreferredWidth(40);
                table.getColumnModel().getColumn(1).setPreferredWidth(130);
                table.getColumnModel().getColumn(2).setPreferredWidth(60);
                table.getColumnModel().getColumn(3).setPreferredWidth(170);
                table.getColumnModel().getColumn(4).setPreferredWidth(50);
                
                if(search.equals("")){
                    table.setModel(model);
                    table.getColumnModel().getColumn(0).setPreferredWidth(40);
                    table.getColumnModel().getColumn(1).setPreferredWidth(130);
                    table.getColumnModel().getColumn(2).setPreferredWidth(60);
                    table.getColumnModel().getColumn(3).setPreferredWidth(170);
                    table.getColumnModel().getColumn(4).setPreferredWidth(50);
                }
            }
        });
        
        //get data from Jtable by click on the row of the table
        table.addMouseListener(new MouseAdapter() 
        {
            public void mouseClicked(MouseEvent evt)
            {
                int row = table.getSelectedRow();
                String rowSelected0=(String) table.getModel().getValueAt(row, 0);
                String rowSelected1=(String) table.getModel().getValueAt(row, 1);
                String rowSelected2=(String) table.getModel().getValueAt(row, 2);
                String rowSelected3=(String) table.getModel().getValueAt(row, 3);
                double rowSelected4=(double) table.getModel().getValueAt(row, 4);
                String rowSelected5 = new Double(rowSelected4).toString();
                jtfFoodId.setText(rowSelected0);
                jtfFoodName.setText(rowSelected1);
                jcbFoodType.setSelectedItem(rowSelected2);
                jtfFoodDescription.setText(rowSelected3);
                jtfFoodPrice.setText(rowSelected5);
                
                
            }
        });
      
        jbtDelete.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e){
                foodControl.deleteData(jtfFoodId.getText());
                model.removeRow(table.getSelectedRow());
                
                
                
            }
        
        });
        
        jbtAdd.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e){
                AddFoodMenu addFoods=new AddFoodMenu();
            }
            
        });
        
        
        
        jbtRefresh.addActionListener(new ActionListener(){
              
            public void actionPerformed(ActionEvent e){
                
                int rowCount = model.getRowCount();
                //Remove rows one by one from the end of the table
                for (int i = rowCount - 1; i >= 0; i--) {
                    model.removeRow(i);
                    
                }       
        
                FoodControl displayFoods=new FoodControl();
        
                ArrayList<Food> foodList = new ArrayList<Food>();
                foodList = displayFoods.getAllRecord();
        
                Object[] rowData = new Object[5];
        
                for(int i = 0; i < foodList.size(); i++){
            
                    rowData[0] = foodList.get(i).getID();
                    rowData[1] = foodList.get(i).getName();
                    rowData[2] = foodList.get(i).getType();
                    rowData[3] = foodList.get(i).getDescription();
                    rowData[4] = foodList.get(i).getPrice();
            
                    model.addRow(rowData);
                }
        
        
        
        
        
                table.setModel(model);
                
                
        }
        
        });
        
        
        //listener event for seat==================================================================
        jtfName.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent e){
                String search=jtfName.getText();
                
                DefaultTableModel model4 = new DefaultTableModel();
                model4.setColumnIdentifiers(heading2);
                SeatControl searchSeats=new SeatControl();
        
                ArrayList<Seat> seatList2 = new ArrayList<Seat>();
                seatList2 = searchSeats.getAllRecord();
        
                Object[] seatData2 = new Object[5];
        
                for(int i = 0; i < seatList2.size(); i++){
            
                    seatData2[0] = seatList2.get(i).getName();
                    seatData2[1] = seatList2.get(i).getDate();
                    seatData2[2] = seatList2.get(i).getTime();
                    seatData2[3] = seatList2.get(i).getNoOfPeople();
                    seatData2[4] = seatList2.get(i).getStatus();
                       
                    if(seatList2.get(i).getName().contains(jtfName.getText())){
                        model4.addRow(seatData2);
                    }
                }
        
                table2.setModel(model4);
                
                table2.getColumnModel().getColumn(0).setPreferredWidth(150);
                table2.getColumnModel().getColumn(1).setPreferredWidth(80);
                table2.getColumnModel().getColumn(2).setPreferredWidth(40);
                table2.getColumnModel().getColumn(3).setPreferredWidth(90);
                table2.getColumnModel().getColumn(4).setPreferredWidth(90);
                
                if(search.equals("")){
                    table2.setModel(model3);
                
                    table2.getColumnModel().getColumn(0).setPreferredWidth(150);
                    table2.getColumnModel().getColumn(1).setPreferredWidth(80);
                    table2.getColumnModel().getColumn(2).setPreferredWidth(40);
                    table2.getColumnModel().getColumn(3).setPreferredWidth(90);
                    table2.getColumnModel().getColumn(4).setPreferredWidth(90);
                }
            }
        });
        
        table2.addMouseListener(new MouseAdapter() 
        {
            public void mouseClicked(MouseEvent evt)
            {
                int row = table2.getSelectedRow();
                String rowSelected0=(String) table2.getModel().getValueAt(row, 0);
                String rowSelected1=(String) table2.getModel().getValueAt(row, 1);
                String rowSelected2=(String) table2.getModel().getValueAt(row, 2);
                int rowSelected3=(Integer) table2.getModel().getValueAt(row, 3);
                String rowSelected4 = new Integer(rowSelected3).toString();
                String rowSelected5=(String) table2.getModel().getValueAt(row, 4);
                
                jtfeName.setText(rowSelected0);
                jtfeDate.setText(rowSelected1);
                jtfeTime.setText(rowSelected2);
                jcbeNoOfPeople.setSelectedItem(rowSelected4);
                jcbeStatus.setSelectedItem(rowSelected5);
                
                
            }
        });
        
        jbtSeatAdd.addActionListener(new ActionListener(){
              
            public void actionPerformed(ActionEvent e){
                new AddSeat();
            }
            
        });
        
        jbtSeatDelete.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e){
                seatControl.deleteData(jtfeName.getText());
                model3.removeRow(table2.getSelectedRow());
                
                
                
            }
        
        });
        
        jbtSeatRefresh.addActionListener(new ActionListener(){
              
            public void actionPerformed(ActionEvent e){
                int rowCount = model3.getRowCount();
                //Remove rows one by one from the end of the table
                for (int i = rowCount - 1; i >= 0; i--) {
                    model3.removeRow(i);
                    
                }       
        
                SeatControl displaySeats=new SeatControl();
        
                ArrayList<Seat> seatList = new ArrayList<Seat>();
                seatList = displaySeats.getAllRecord();
                
                Object[] seatData = new Object[5];
        
        
                for(int i = 0; i < seatList.size(); i++){
            
                    seatData[0] = seatList.get(i).getName();
                    seatData[1] = seatList.get(i).getDate();
                    seatData[2] = seatList.get(i).getTime();
                    seatData[3] = seatList.get(i).getNoOfPeople();
                    seatData[4] = seatList.get(i).getStatus();
            
                    model3.addRow(seatData);
                }
        
        
        
        
                table2.setModel(model3);
              
        }
        
        });
        
        
        jbtSeatEdit.addActionListener(new editSeatListenerClass());
        jbtSeatSave.addActionListener(new saveSeatListenerClass());
        
        setSize(900,700);
        
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void updateTable(){
         int rowCount = model.getRowCount();
                //Remove rows one by one from the end of the table
                for (int i = rowCount - 1; i >= 0; i--) {
                    model.removeRow(i);
                    
                }       
        
                FoodControl displayFoods=new FoodControl();
        
                ArrayList<Food> foodList = new ArrayList<Food>();
                foodList = displayFoods.getAllRecord();
        
                Object[] rowData = new Object[5];
        
                for(int i = 0; i < foodList.size(); i++){
            
                    rowData[0] = foodList.get(i).getID();
                    rowData[1] = foodList.get(i).getName();
                    rowData[2] = foodList.get(i).getType();
                    rowData[3] = foodList.get(i).getDescription();
                    rowData[4] = foodList.get(i).getPrice();
            
                    model.addRow(rowData);
                }
        
        
        
        
        
                table.setModel(model);
    }
    
    //Food listener classes
    
    private class editListenerClass implements ActionListener{
        public void actionPerformed(ActionEvent e) {
         
             jtfFoodName.setEditable(true);
             jtfFoodId.setEditable(true);
             jtfFoodDescription.setEditable(true);
             jtfFoodPrice.setEditable(true);
             jbtSave.setEnabled(true);
             
         }
    }
    
    private class saveListenerClass implements ActionListener{
         public void actionPerformed(ActionEvent e) {
             jtfFoodName.setEditable(false);
             jtfFoodId.setEditable(false);
             //jcbFoodType.setEditable(false);
             jtfFoodDescription.setEditable(false);
             jtfFoodPrice.setEditable(false);
             jbtSave.setEnabled(false);
             double price=Double.parseDouble(jtfFoodPrice.getText());
             foodControl.updateData(jtfFoodId.getText(),jtfFoodName.getText(),jcbFoodType.getSelectedItem().toString(),jtfFoodDescription.getText(),price);
             updateTable();
         }
    }
    
    //seat listener classes
    private class editSeatListenerClass implements ActionListener{
         public void actionPerformed(ActionEvent e) {
             //jtfeName.setEditable(true);
             jtfeDate.setEditable(true);
             jtfeTime.setEditable(true);
             //jcbeNoOfPeople.setEditable(true);
             //jcbeStatus.setEditable(true);
             jbtSeatSave.setEnabled(true);
             
             
         }
    }
    
    private class saveSeatListenerClass implements ActionListener{
         public void actionPerformed(ActionEvent e) {
             //jtfeName.setEditable(false);
             jtfeDate.setEditable(false);
             jtfeTime.setEditable(false);
             jcbeNoOfPeople.setEditable(false);
             jcbeStatus.setEditable(false);
             jbtSeatSave.setEnabled(false);
             int num=Integer.parseInt(jcbeNoOfPeople.getSelectedItem().toString());
             seatControl.updateData(jtfeName.getText(), jtfeDate.getText(), jtfeTime.getText(),num , jcbeStatus.getSelectedItem().toString());
             
             
         }
    }
    
    
    
 
    
    public static void main(String[] args) {
        new ReadFoodMenu();
    }
}
