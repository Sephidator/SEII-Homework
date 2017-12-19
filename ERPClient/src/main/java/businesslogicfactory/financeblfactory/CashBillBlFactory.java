package main.java.businesslogicfactory.financeblfactory;

import main.java.businesslogic.financebl.CashBillBl;
import main.java.businesslogicservice.financeblservice.CashBillBlService;

public class CashBillBlFactory {
    public static CashBillBlService getService(){
        CashBillBlService cashBillBlService = new CashBillBl();
        return cashBillBlService;
    }
}
