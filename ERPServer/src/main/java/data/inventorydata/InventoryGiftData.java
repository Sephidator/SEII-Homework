package main.java.data.inventorydata;

import main.java.dataservice.inventorydataservice.InventoryGiftBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.inventorybill.InventoryGiftBillPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class InventoryGiftData implements InventoryGiftBillDataService{
    @Override
    public ArrayList<InventoryGiftBillPO> finds(BillQueryPO query) throws RemoteException {
        return null;
    }

    @Override
    public String insert(InventoryGiftBillPO bill) throws RemoteException {
        return null;
    }

    @Override
    public void update(InventoryGiftBillPO bill) throws RemoteException {

    }
}
