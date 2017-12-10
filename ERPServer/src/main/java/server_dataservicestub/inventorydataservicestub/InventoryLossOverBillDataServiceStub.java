package main.java.server_dataservicestub.inventorydataservicestub;

import main.java.dataservice.inventorydataservice.InventoryLossOverBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.inventorybill.InventoryLossOverBillPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class InventoryLossOverBillDataServiceStub implements InventoryLossOverBillDataService {

    @Override
    public ArrayList<InventoryLossOverBillPO> finds(BillQueryPO query) throws RemoteException {
        ArrayList<InventoryLossOverBillPO> list = new ArrayList<>();
        list.add(new InventoryLossOverBillPO());
        return list;
    }

    @Override
    public String insert(InventoryLossOverBillPO po) throws RemoteException {
        return "KCYSD-20171212-12345";
    }

    @Override
    public void update(InventoryLossOverBillPO po) throws RemoteException {

    }

}
