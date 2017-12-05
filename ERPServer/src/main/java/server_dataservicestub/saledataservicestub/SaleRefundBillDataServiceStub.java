package main.java.server_dataservicestub.saledataservicestub;

import main.java.dataservice.saledataservice.SaleRefundBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.salebill.SaleRefundBillPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class SaleRefundBillDataServiceStub implements SaleRefundBillDataService {

    @Override
    public ArrayList<SaleRefundBillPO> finds(BillQueryPO query) {
        ArrayList<SaleRefundBillPO> list = new ArrayList<>();
        list.add(new SaleRefundBillPO());
        return list;
    }

    @Override
    public String insert(SaleRefundBillPO po) {
        return "XSTHD-20171212-12345";
    }

    @Override
    public void update(SaleRefundBillPO po) {
    }

}