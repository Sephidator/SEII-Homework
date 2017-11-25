package main.java.dataservice.inventorydataservice;

import main.java.data.datautility.ResultMessage;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.inventorybill.InventoryGiftBillPO;

public interface InventoryGiftBillDataService {

    public InventoryGiftBillPO find(BillQueryPO query);

    public ResultMessage insert(InventoryGiftBillPO bill);

    public ResultMessage update(InventoryGiftBillPO bill);
}
