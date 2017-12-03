package main.java.server_dataservicestub.inventorydataservicestub;

import main.java.dataservice.inventorydataservice.InventoryGiftBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.inventorybill.InventoryGiftBillPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class InventoryGiftBillDataServiceStub implements InventoryGiftBillDataService {

    @Override
    public ArrayList<InventoryGiftBillPO> find(BillQueryPO query) throws RemoteException {
        ArrayList<InventoryGiftBillPO> list = new ArrayList<>();
        list.add(new InventoryGiftBillPO());
        return list;
    }

    @Override
    public String insert(InventoryGiftBillPO bill) throws RemoteException {
        return "KCZSD-20171212-12345";
    }

    @Override
    public void update(InventoryGiftBillPO bill) throws RemoteException {

    }

}
