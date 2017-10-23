package dataservice.editbilldataservice;

import po.InventoryBillPO;
import po.PurchaseBillPO;
import po.SaleBillPO;

import java.util.ArrayList;

public interface EditPurchaseSaleBillDataService {
    public ArrayList<PurchaseBillPO> getPurchaseBill(int state);
    public ArrayList<SaleBillPO> getSaleBill(int state);
}
