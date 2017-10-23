package dataservice.editbilldataservice;

import po.InventoryBillPO;

import java.util.ArrayList;

public interface EditInventoryBillDataService {
    public ArrayList<InventoryBillPO> getInventoryBill(int state);
}
