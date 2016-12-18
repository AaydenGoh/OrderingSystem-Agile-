package ui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import control.OrderControl;
import domain.*;
import ui.*;
import java.util.ArrayList;
import javax.swing.table.*;

public class test1 extends JFrame{
    
    private OrderControl odrControl;
    private JPanel panel[] = {new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(new GridLayout(1,6)), new JPanel()};
    private JLabel label[] = {new JLabel("ITEM"), new JLabel("Item ID"), new JLabel("Name"), new JLabel("Quantity"), new JLabel("Price"),new JLabel("Search:")};
    private JTextField text[] = {new JTextField(), new JTextField(), new JTextField(),  new JTextField(),new JTextField()};
    private JButton buttonAdmin[] = {new JButton("Staff/Member DB"), new JButton("Food/Seat DB")};
    private JButton buttonOrder[] = {new JButton("Auto fill-in"), new JButton("Reset"), new JButton("Confirm")};
    private JButton buttonList[] = {new JButton("Search"), new JButton("DELETE"), new JButton("CANCEL"), new JButton("COMPUTE PAYMENT")};
    private JTable table = new JTable(); 
    private DefaultTableModel model = new DefaultTableModel();
    private String [] heading = {"ORDER ID","OrderCount","ID","ITEM NAME", "QUANTITY","PRICE"};
    private static int orderIdCount = 1;
    private String[] colName = new String[label.length];
    private ArrayList<Order> order = new ArrayList<Order>();
    private JScrollPane pane = new JScrollPane();
    private static int orderId = 1000;
    public test1(){
        odrControl = new OrderControl();
        
        tableDesign();
        
        panel[0].setLayout(new BorderLayout());
        panel[1].setPreferredSize(new Dimension(550, 35));
        
        panel[9].setBorder(BorderFactory.createLineBorder(Color.yellow, 10));
        panel[2].setBorder(BorderFactory.createLineBorder(Color.yellow, 10));
        panel[7].setBorder(BorderFactory.createLineBorder(Color.green, 10));
        panel[3].setBorder(BorderFactory.createLineBorder(Color.black, 10));
        panel[4].setBorder(BorderFactory.createLineBorder(Color.black, 10));
        panel[5].setBorder(BorderFactory.createLineBorder(Color.blue, 10));
        
        Font font = new Font("Verdana", Font.BOLD, 50);
        label[0].setFont(font);
        
        panel[1].setLayout(new FlowLayout(FlowLayout.CENTER));
        for(int a=0; a<buttonAdmin.length; a++){
            panel[1].add(buttonAdmin[a]);
        }
        buttonAdmin[1].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new ReadFoodMenu();
            }
        });
        buttonAdmin[0].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new DataManageFrame();
            }
        });
        
        panel[9].setLayout(new BorderLayout());
        panel[9].add(panel[2], BorderLayout.WEST);
        panel[9].add(panel[7], BorderLayout.CENTER);
        panel[2].setLayout(new BorderLayout());
        panel[2].setPreferredSize(new Dimension(500,0));
        panel[4].setPreferredSize(new Dimension(200,0));
        panel[3].setPreferredSize(new Dimension(300,0));
        panel[2].add(panel[3], BorderLayout.WEST);
        panel[2].add(panel[4], BorderLayout.EAST);
        panel[3].setLayout(new BorderLayout(500,0));
        panel[3].add(panel[5], BorderLayout.NORTH);
        panel[3].add(panel[6], BorderLayout.SOUTH);
        panel[6].setPreferredSize(new Dimension(50,250));
        panel[5].add(label[0]);
        panel[6].setLayout(new GridLayout(4,2, 0, 60));
        panel[6].add(label[1]);
        panel[6].add(text[0]);
        panel[6].add(label[2]);
        panel[6].add(text[1]);
        panel[6].add(label[3]);
        panel[6].add(text[2]);
        panel[6].add(label[4]);
        panel[6].add(text[3]);

        panel[4].setLayout(new GridLayout(3,1, 200, 120));
        panel[4].add(buttonOrder[0]);
        panel[4].add(buttonOrder[1]);
        panel[4].add(buttonOrder[2]);
        buttonOrder[0].setPreferredSize(new Dimension(10, 20));
        panel[7].add(table);
        
        panel[8].add(label[5]);
        panel[8].add(text[4]);
        for(int a=0; a<buttonList.length; a++){
            panel[8].add(buttonList[a]);
        }
        table.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                
                int row = table.getSelectedRow(); 
                String rowSelected1=(String) table.getValueAt(row, 2);
                String rowSelected2=(String) table.getValueAt(row, 3);
                int rowSelected3=(int) table.getValueAt(row, 4);
                double rowSelected4=(double) table.getValueAt(row, 5);
                text[0].setText(rowSelected1);
                text[1].setText(rowSelected2);
                text[2].setText(String.valueOf(rowSelected3));
                text[3].setText(String.valueOf(rowSelected4));
                //order.remove(row);
                //model.removeRow(row);
            }
        });
        
        panel[0].add(panel[1], BorderLayout.NORTH);
        panel[0].add(panel[9], BorderLayout.CENTER);
        panel[0].add(panel[8], BorderLayout.SOUTH);
        add(panel[0]);
        
        buttonOrder[0].addActionListener(new retrieveClass());
        buttonOrder[1].addActionListener(new resetClass());
        buttonOrder[2].addActionListener(new confirmClass());
        buttonList[0].addActionListener(new searchClass());
        buttonList[1].addActionListener(new deleteClass());
        buttonList[2].addActionListener(new cancelClass());
        buttonList[3].addActionListener(new paymentClass());
        
    }
    
    private void tableDesign(){
        JScrollPane jScrollPane1 = new JScrollPane();
        //jScrollPane1.setViewportView(table);
        
        model.setColumnIdentifiers(heading);
        table.setModel(model);
        jScrollPane1.add(table);
        
    }
    
    private class retrieveClass implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String id = text[0].getText();
            Food food = odrControl.selectRecord(id);
            if(food != null){
                text[1].setText(food.getName());
                text[3].setText(String.valueOf(food.getPrice()));
            } else {
                JOptionPane.showMessageDialog(null, "No such ID code.", "Record not found in Database", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private class resetClass implements ActionListener{
        public void actionPerformed(ActionEvent e){
            for(int a=0;a<text.length;a++){
                text[a].setText("");
            }
        }
    }
    
    private class confirmClass implements ActionListener{
        public void actionPerformed(ActionEvent e){
            double quantity = Double.valueOf(text[2].getText());
            //efaultTableModel model = (DefaultTableModel) table.getModel();
            //if(!text[1].getText().trim().equals("")){
                orderId = Integer.valueOf(odrControl.getOrderID());
                orderIdCount = odrControl.getOrderCount();
                orderId++;
                model.addRow(new Object[]{orderId,orderIdCount,text[0]. getText(), text[1].getText(), text[2].getText(), text[3].getText()});
                order.add(new Order(String.valueOf(orderId),orderIdCount,text[0]. getText(), text[1].getText(), Integer.valueOf(text[2].getText()), Double.valueOf(text[3].getText())));
                odrControl.createRecord(String.valueOf(orderId),orderIdCount,text[0]. getText(), text[1].getText(), Integer.valueOf(text[2].getText()), Double.valueOf(text[3].getText()));
                
                System.out.print(Integer.valueOf(odrControl.getOrderID()));
                orderId++;
                
            //} else {
                //JOptionPane.showMessageDialog(null, "Value must be entered before confirming", "ERROR", JOptionPane.ERROR_MESSAGE);
            //}
        
        }
    }

    private class searchClass implements ActionListener{
        public void actionPerformed(ActionEvent e){
            int orderCount=Integer.parseInt(text[4].getText());
            
            ArrayList<Order> orders=new ArrayList<Order>();
            orders= odrControl.searchRecord(orderCount);
            Object [] rowData=new Object[6];
            for(int i=0;i<orders.size();i++){
                rowData[0]=orders.get(i).getOrderID();
                rowData[1]=orders.get(i).getOrderCount();
                rowData[2]=orders.get(i).getFoodID();
                rowData[3]=orders.get(i).getFoodName();
                rowData[4]=orders.get(i).getQty();
                rowData[5]=orders.get(i).getPrice();
                
                model.addRow(rowData);
            }
            table.setModel(model);
        }
    }
    
    private class deleteClass implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //String OrderID=order.get(table.getSelectedRow()).getOrderID();
            String orderID=table.getValueAt(table.getSelectedRow(), 0).toString();
            odrControl.deleteRecord(orderID);
            //order.remove(table.getSelectedRow());
            model.removeRow(table.getSelectedRow());
        }
    }
    
    private class cancelClass implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //reset all record in table
            for(int i=table.getRowCount()-1;i>=0;i--)
                model.removeRow(i);
            order.clear();
            
        }
    }
    
    private class paymentClass implements ActionListener{
        public void actionPerformed(ActionEvent e){
            new Payment(orderIdCount);
            orderIdCount=odrControl.getOrderCount();
            orderIdCount++;
            for(int i=order.size()-1;i>=0;i--)
                model.removeRow(i);
            order.clear();
            
        }
    }
    
    public static void main(String[] args) {
        test1 frame = new test1();
        frame.setTitle("Restaurant POS");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1250, 550);    
    }
}
