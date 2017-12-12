package main.java.data;

import main.java.data.financedata.CashBillData;
import main.java.dataservice.financedataservice.CashBillDataService;
import main.java.po.bill.financebill.CashBillPO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

public class Test {

    public static void main(String[] args) throws RemoteException {
        DataHelper.init();
        CashBillDataService service = new CashBillData();
        System.out.println(service.insert(new CashBillPO("草稿", new Date(), "User00000001", "nothing", 2000, "Account00000001", new ArrayList<>())));
    }
}
