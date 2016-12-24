// author : Ee Wing Fei
package control;

import da.PaymentDA;
import domain.*;
import java.util.ArrayList;
public class PaymentControl {

    private PaymentDA payDA;

    public PaymentControl() {
        payDA = new PaymentDA();
    }

    
    public ArrayList<Order> getBill(int orderCount) {
        return payDA.getBill(orderCount);
    }
}
