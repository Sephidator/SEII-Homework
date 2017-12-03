package main.java.server_dataservicestub.purchasedataservicestub;

import main.java.dataservice.purchasedataservice.PurchaseTradeBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.purchasebill.PurchaseTradeBillPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class PurchaseTradeBillDataServiceStub implements PurchaseTradeBillDataService{
    @Override
    public ArrayList<PurchaseTradeBillPO> find(BillQueryPO query) {
        ArrayList<PurchaseTradeBillPO> list=new ArrayList<>();
        list.add(new PurchaseTradeBillPO());
        return list;
    }

    @Override
    public String insert(PurchaseTradeBillPO po) throws RemoteException {
        return "JHD-20171212-12345";
    }

    @Override
    public void update(PurchaseTradeBillPO po) throws RemoteException {

    }

}

