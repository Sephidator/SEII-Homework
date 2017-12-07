package main.java.data_stub.purchasedataservicestub;

import main.java.dataservice.purchasedataservice.PurchaseRefundBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.purchasebill.PurchaseRefundBillPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class PurchaseRefundBillDataServiceStub implements PurchaseRefundBillDataService {
    @Override
    public ArrayList<PurchaseRefundBillPO> finds(BillQueryPO query) {
        ArrayList<PurchaseRefundBillPO> list = new ArrayList<>();
        list.add(new PurchaseRefundBillPO());
        return list;
    }

    @Override
    public String insert(PurchaseRefundBillPO po)  {
        return "JHTHD-20171212-12345";
    }

    @Override
    public void update(PurchaseRefundBillPO po)  {

    }
}
