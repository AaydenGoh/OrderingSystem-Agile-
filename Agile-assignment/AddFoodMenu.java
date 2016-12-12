package ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import control.FoodControl;
public class AddFoodMenu extends JFrame{
//variable
    private String [] foodTypeList={"Meat","SeaFood","Pasta","Rice","Drink"};
    
//component
    private JLabel jlbNewProduct=new JLabel("New Dish");
    private JLabel jlbFoodName=new JLabel("Food Name");
    private JLabel jlbFoodId=new JLabel("Food ID");
    private JLabel jlbFoodType=new JLabel("Food Type");
    private JLabel jlbFoodDescription=new JLabel("Food Description");
    private JLabel jlbFoodPrice=new JLabel("Food Price");
    
    private JTextField jtfFoodaName=new JTextField();
    private JTextField jtfFoodaId=new JTextField();
    private JTextField jtfFoodaDescription=new JTextField();
    private JTextField jtfFoodaPrice=new JTextField();
    private JComboBox jcbFoodaType=new JComboBox(foodTypeList);
  
    private JButton jbtAdd=new JButton("Add");
    private JButton jbtCancel=new JButton("Cancel");
    
    private FoodControl foodControl= new FoodControl();
//===================================================================================================    
    public AddFoodMenu(){
        JPanel addPanel0=new JPanel();
        JPanel addPanel1=new JPanel();
        JPanel addPanel2=new JPanel();
        
        setTitle("New Product");
        jlbNewProduct.setHorizontalAlignment(SwingConstants.CENTER);
        jlbNewProduct.setFont(new Font("SansSerif", Font.BOLD+Font.ITALIC, 30));
        addPanel0.add(jlbNewProduct);
        addPanel0.setLayout(new GridLayout(1,1));
        
        addPanel1.add(jlbFoodName);
        addPanel1.add(jtfFoodaName);
        
        addPanel1.add(jlbFoodId);
        addPanel1.add(jtfFoodaId);
        
        addPanel1.add(jlbFoodType);
        addPanel1.add(jcbFoodaType);
        
        addPanel1.add(jlbFoodDescription);
        addPanel1.add(jtfFoodaDescription);
        
        addPanel1.add(jlbFoodPrice);
        addPanel1.add(jtfFoodaPrice);
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
                String ID=jtfFoodaId.getText();
                String name=jtfFoodaName.getText();
                String type=(String) jcbFoodaType.getSelectedItem();
                String description=jtfFoodaDescription.getText();
                double price =Double.parseDouble(jtfFoodaPrice.getText());
                foodControl.addData(ID,name,type,description,price);
                
                JOptionPane.showMessageDialog(null, "Successful add new dish", "Successful", JOptionPane.INFORMATION_MESSAGE);

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
        new AddFoodMenu();
        
    }
}
