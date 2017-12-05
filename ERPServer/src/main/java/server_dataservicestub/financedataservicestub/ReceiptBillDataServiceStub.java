package main.java.server_dataservicestub.financedataservicestub;

import main.java.dataservice.financedataservice.ReceiptBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.financebill.ReceiptBillPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ReceiptBillDataServiceStub implements ReceiptBillDataService {
    @Override
    public ArrayList<ReceiptBillPO> finds(BillQueryPO query) {
        ReceiptBillPO receiptBillPO = new ReceiptBillPO();
        ArrayList<ReceiptBillPO> receiptBillPOS = new ArrayList<>();
        receiptBillPOS.add(receiptBillPO);
        return receiptBillPOS;
    }

    @Override
    public String insert(ReceiptBillPO po)  {
        return "SKD-20171212-12345";
    }

    @Override
    public void update(ReceiptBillPO po){

    }

}
