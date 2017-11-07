package dataservice.inventorydataservice;

import businesslogic.blutility.ResultMessage;
import po.bill.BillQueryPO;
import po.bill.inventorybill.InventoryGiftBillPO;

public interface InventoryGiftBillDataService {

    public InventoryGiftBillPO find(BillQueryPO query);

    public ResultMessage insert(InventoryGiftBillPO bill);

    public ResultMessage update(InventoryGiftBillPO bill);
}
