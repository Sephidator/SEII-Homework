package main.java.server_dataservicestub.saledataservicestub;

import main.java.dataservice.saledataservice.SaleTradeBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.salebill.SaleTradeBillPO;
import main.java.po.bill.salebill.SaleTradeBillQueryPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class SaleTradeBillDataServiceStub implements SaleTradeBillDataService {
    @Override
    public ArrayList<SaleTradeBillPO> findsByReport(SaleTradeBillQueryPO query) throws RemoteException {
        ArrayList<SaleTradeBillPO> list = new ArrayList<>();
        list.add(new SaleTradeBillPO());
        return list;
    }

    @Override
    public ArrayList<SaleTradeBillPO> findsByBill(BillQueryPO query) {
        ArrayList<SaleTradeBillPO> list = new ArrayList<>();
        list.add(new SaleTradeBillPO());
        return list;
    }

    @Override
    public String insert(SaleTradeBillPO po) {
        return "XSD-20171212-12345";
    }

    @Override
    public void update(SaleTradeBillPO po) {

    }

}
