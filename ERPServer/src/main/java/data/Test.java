package main.java.data;

import main.java.data.financedata.CashBillData;
import main.java.dataservice.financedataservice.CashBillDataService;
import main.java.po.bill.financebill.CashBillPO;

import java.rmi.RemoteException;

public class Test {
    public static void main(String[] args) throws RemoteException {
        DataHelper.init();
        CashBillDataService service = new CashBillData();
        service.insert(new CashBillPO());
    }
}
