package main.java.businesslogicfactory.financeblfactory;

import main.java.businesslogic.financebl.PaymentBillBl;
import main.java.businesslogicservice.financeblservice.PaymentBillBlService;

public class PaymentBillBlFactory {
    public static PaymentBillBlService getService(){
        PaymentBillBlService paymentBillBlService = new PaymentBillBl();
        return paymentBillBlService;
    }
}
