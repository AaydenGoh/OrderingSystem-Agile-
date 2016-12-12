package ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import control.SeatControl;
public class AddSeat extends JFrame{
//variable
    private String [] heading2 = {"Name","Date", "Time", "No Of People", "Status" };
    private String [] number = {"1","2","3","4","5","6","7","8","9","10","11","12"};
    private String [] reserve2 = {"Cancelled", "Reserved","Waiting"};
    
//component
    private JLabel jlbNewSeat=new JLabel("Add New Seat");
    private JLabel jlbSeatName=new JLabel("Customer Name");
    private JLabel jlbSeatDate=new JLabel("Date");
    private JLabel jlbSeatTime=new JLabel("Time");
    private JLabel jlbSeatNoOfPeople=new JLabel("No Of People");
    private JLabel jlbSeatStatus=new JLabel("Status");
    
    private JTextField jtfSeatName=new JTextField();
    private JTextField jtfSeatDate=new JTextField();
    private JTextField jtfSeatTime=new JTextField();
    private JComboBox jcbSeatNoOfPeople=new JComboBox(number);
    private JComboBox jcbSeatStatus=new JComboBox(reserve2);
  
    private JButton jbtAdd=new JButton("Add");
    private JButton jbtCancel=new JButton("Cancel");
    
    private SeatControl seatControl= new SeatControl();
//===================================================================================================    
    public AddSeat(){
        JPanel addPanel0=new JPanel();
        JPanel addPanel1=new JPanel();
        JPanel addPanel2=new JPanel();
        
        setTitle("New Seat");
        jlbNewSeat.setHorizontalAlignment(SwingConstants.CENTER);
        jlbNewSeat.setFont(new Font("SansSerif", Font.BOLD+Font.ITALIC, 30));
        addPanel0.add(jlbNewSeat);
        addPanel0.setLayout(new GridLayout(1,1));
        
        addPanel1.add(jlbSeatName);
        addPanel1.add(jtfSeatName);
        
        addPanel1.add(jlbSeatDate);
        addPanel1.add(jtfSeatDate);
        
        addPanel1.add(jlbSeatTime);
        addPanel1.add(jtfSeatTime);
        
        addPanel1.add(jlbSeatNoOfPeople);
        addPanel1.add(jcbSeatNoOfPeople);
        
        addPanel1.add(jlbSeatStatus);
        addPanel1.add(jcbSeatStatus);
        addPanel1.setLayout(new GridLayout(5,2,50,30));
        addPanel1.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
            
        addPanel2.setLayout(new FlowLayout(FlowLayout.RIGHT));
        addPanel2.add(jbtAdd);
        addPanel2.add(jbtCancel);
        addPanel2.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        
        add(addPanel0, BorderLayout.NORTH);
        add(addPanel1, BorderLayout.CENTER);
        add(addPanel2, BorderLayout.SOUTH);
        
        jbtAdd.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e){
                String name=jtfSeatName.getText();
                String date=jtfSeatDate.getText();
                String time=jtfSeatTime.getText();
                String noOfPeople=jcbSeatNoOfPeople.getSelectedItem().toString();
                int noOfpeople=Integer.parseInt(noOfPeople);
                String status=jcbSeatStatus.getSelectedItem().toString();
                seatControl.addRecord(name, date, time, noOfpeople, status);
                
                JOptionPane.showMessageDialog(null, "Successful add Seat", "Successful", WIDTH);
            }
        
        });
        
        jbtCancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                close();
            }
        
        });
        
        setSize(400,350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        
    }
    
    public void close(){

        WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOS­ING);
        Toolkit.getDefaultToolkit().getSystemEve­ntQueue().postEvent(winClosingEvent);

    }
    
    public static void main(String[] args) {
        new AddSeat();
        
    }
}
