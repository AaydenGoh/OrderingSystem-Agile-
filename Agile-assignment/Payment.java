
package ui;
// author : Ee Wing Fei
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import control.*;
import domain.*;
import java.io.*;
import java.util.ArrayList;


public class Payment extends JFrame{
    private String msg = "Total : RM";
    private JLabel total;
    private Font a = new Font("SansSerif ", Font.ITALIC, 52);
    private JComboBox box = new JComboBox(new String[]{"Cash","Debit/Credit Card"});
    private JLabel[] card = {new JLabel("Credit/Debit Card Number"),new JLabel("CCV")};
    private JLabel[] cash = {new JLabel("Cash"),new JLabel("Balance")};
    private JTextField id = new JTextField();
    private JTextField[] input = new JTextField[card.length];
    private JTextField[] inputCash = new JTextField[cash.length];
    //private ImageIcon [] icon = {new ImageIcon(getClass().getResource("images/visa.gif")),
    //                             new ImageIcon(getClass().getResource("images/master.png"))};
    private JButton[] but = {new JButton("Visa"),new JButton("Master")}; 
    private JButton ok = new JButton("OK");
    private JPanel[] panel = {new JPanel(),
                              new JPanel(new GridLayout(3,2)),
                              new JPanel(),
                              new JPanel(new GridLayout(3,2)),
                              new JPanel()};
    private PaymentControl pc = new PaymentControl();
    private ArrayList<Order> or;
    private double totalAmt;
    public Payment(int orderCount){
        or = pc.getBill(orderCount);
        totalAmt = calculatePayment(or);
        
        msg += " "+String.format("%.2f",totalAmt);
        total  = new JLabel(msg);
        panel[0].add(total);
        total.setFont(a);
        panel[1].add(new JLabel("Payment method"));
        panel[1].add(box);
        panel[2].add(but[0]);
        panel[2].add(but[1]);
        for(int i=0;i<card.length;i++){
            input[i] = new JTextField();
            panel[1].add(card[i]);
            panel[1].add(input[i]);
            input[i].setEditable(false);
        }
        for(int i=0;i<cash.length;i++){
            inputCash[i] = new JTextField();
            panel[3].add(cash[i]);
            panel[3].add(inputCash[i]);
        }
        inputCash[1].setEditable(false);
        JButton invi = new JButton();
        panel[3].add(invi);
        invi.setVisible(false);
        panel[3].add(ok);
      
        box.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(box.getSelectedItem()==(Object)("Debit/Credit Card")){
                    input[0].setEditable(true);
                    input[1].setEditable(true);
                    inputCash[0].setEditable(false);
                    inputCash[0].setText("-");
                }else if(box.getSelectedItem()==(Object)("Cash")){
                    input[0].setEditable(false);
                    input[1].setEditable(false);
                    inputCash[0].setEditable(true);
                    inputCash[0].setText("");
                }
                    
            }
        });
        
        
        add(panel[0],BorderLayout.NORTH);
        add(panel[1],BorderLayout.CENTER);
        add(panel[2],BorderLayout.EAST);
        add(panel[3],BorderLayout.SOUTH);
        
        setTitle("Payment");
        pack();
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        ButtonHandler button = new ButtonHandler();
        but[0].addActionListener(button);
        but[1].addActionListener(button);
        ok.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                double amt = Double.valueOf(inputCash[0].getText());
                double balance = amt - totalAmt;
                inputCash[1].setText(String.format("RM %.2f",balance));
                FileWriter file;
                try{
                    file = new FileWriter(new File("receipt.txt"));
                    file.write(String.format("                    HGGE Cafe\n"+
                                             "==================================================\n"+
                                             "%-20s\t%s\t%s\t%s\n","OrderName","UnitPrice","Qty","SubTotal"));
                    String list="";
                    for(int i=0;i<or.size();i++){
                        list += String.format("%-20s\t",or.get(i).getFoodName());
                        
                        list += String.format("%.2f\t\t",or.get(i).getPrice());
                        list += String.format("%d\t",or.get(i).getQty());
                        list += String.format("%.2f\n",or.get(i).getQty()*or.get(i).getPrice());
                    }
                    file.write(list);
                    file.write(String.format("==================================================\n"+
                                             "Total Amount                          RM %.2f\n"+
                                             "Payment                               RM %.2f\n"+
                                             "Balance                               RM %.2f\n"+
                                             "==================================================\n\n\n"+
                                             "Thank you, please come again.",totalAmt,amt,balance));
                    file.write(System.lineSeparator());
                    file.close();
                }catch(IOException ex){
                    ex.printStackTrace();
                }
            }
        });
    }
    private class ButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String inputq = input[0].getText();
            if(e.getSource()==but[0]){
                FileWriter file;
                try{
                    file = new FileWriter(new File("receiptCredit.txt"));
                    file.write(String.format("                    HGGE Cafe\n"+
                                             "==================================================\n"+
                                             "Credit Card Number : %s\n"+
                                             "%-20s\t%s\t%s\t%s\n",inputq,"OrderName","UnitPrice","Qty","SubTotal"));
                    String list="";
                    for(int i=0;i<or.size();i++){
                        list += String.format("%-20s\t",or.get(i).getFoodName());
                        
                        list += String.format("%.2f\t\t",or.get(i).getPrice());
                        list += String.format("%d\t",or.get(i).getQty());
                        list += String.format("%.2f\n",or.get(i).getQty()*or.get(i).getPrice());
                    }
                    file.write(list);
                    file.write(String.format("==================================================\n"+
                                             "Total Amount                          RM %.2f\n"+
                                             "Payment                               RM %.2f\n"+
                                             "Balance                               RM 0.00\n"+
                                             "==================================================\n\n\n"+
                                             "   __________________________________________\n"+
                                             "               Cutomer Copy\n"+
                                             "Thank you, please come again.",totalAmt,totalAmt));
                    file.write(System.lineSeparator());
                    file.close();
                }catch(IOException ex){
                    ex.printStackTrace();
                }
            }else if(e.getSource()==but[1]){
                FileWriter file;
                try{
                    file = new FileWriter(new File("receiptCredit.txt"));
                    file.write(String.format("                    HGGE Cafe\n"+
                                             "==================================================\n"+
                                             "Credit Card Number : %s"+
                                             "%-20s\t%s\t%s\t%s\n",inputq,"OrderName","UnitPrice","Qty","SubTotal"));
                    String list="";
                    for(int i=0;i<or.size();i++){
                        list += String.format("%-20s\t",or.get(i).getFoodName());
                        
                        list += String.format("%.2f\t\t",or.get(i).getPrice());
                        list += String.format("%d\t",or.get(i).getQty());
                        list += String.format("%.2f\n",or.get(i).getQty()*or.get(i).getPrice());
                    }
                    file.write(list);
                    file.write(String.format("==================================================\n"+
                                             "Total Amount                          RM %.2f\n"+
                                             "Payment                               RM %.2f\n"+
                                             "Balance                               RM 0.00\n"+
                                             "==================================================\n\n\n"+
                                             "   __________________________________________\n"+
                                             "               Cutomer Copy\n"+
                                             "Thank you, please come again.",totalAmt,totalAmt));
                    file.write(System.lineSeparator());
                    file.close();
                }catch(IOException ex){
                    ex.printStackTrace();
                }
            }
            
            
        }
    }
    public double calculatePayment(ArrayList<Order> order){
        double totalAmt = 0;
        for(int i=0;i<order.size();i++){
            totalAmt += order.get(i).getQty()*order.get(i).getPrice();
        }
        return totalAmt;
    }
    public static void main(String[] args){
        //new Payment(2);
    }
}
