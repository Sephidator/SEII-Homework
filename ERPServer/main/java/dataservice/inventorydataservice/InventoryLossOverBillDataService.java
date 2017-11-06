package dataservice.inventorydataservice;

import data.datautility.ResultMessage;
import po.bill.BillQueryPO;
import po.bill.inventorybill.InventoryLossOverBillPO;

public interface InventoryLossOverBillDataService {

    public InventoryLossOverBillPO find(BillQueryPO query);

    public ResultMessage insert(InventoryLossOverBillPO bill);

    public ResultMessage update(InventoryLossOverBillPO bill);


}
