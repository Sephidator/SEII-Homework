package main.java.data;

import main.java.data.financedata.ReceiptBillData;
import main.java.dataservice.financedataservice.ReceiptBillDataService;
import main.java.po.bill.BillQueryPO;

import java.rmi.RemoteException;

public class Test {

    public static void main(String[] args) throws RemoteException {
        DataHelper.init();
        ReceiptBillDataService service = new ReceiptBillData();
        System.out.println(service.finds(new BillQueryPO()).size());
    }
}
