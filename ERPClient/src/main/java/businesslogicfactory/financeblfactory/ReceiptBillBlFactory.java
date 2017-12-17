package main.java.businesslogicfactory.financeblfactory;


import main.java.businesslogic.financebl.ReceiptBillBl;
import main.java.businesslogicservice.financeblservice.ReceiptBillBlService;

public class ReceiptBillBlFactory {
    public ReceiptBillBlService getService(){
        ReceiptBillBlService receiptBillBlService = new ReceiptBillBl();
        return receiptBillBlService;
    }
}
