package main.java.businesslogicfactory.financeblfactory;

import main.java.businesslogic.financebl.CashBillBl;
import main.java.businesslogicservice.financeblservice.CashBillBlService;

public class CashBillBlFactory {
    public CashBillBlService getService(){
        CashBillBlService cashBillBlService = new CashBillBl();
        return cashBillBlService;
    }
}
