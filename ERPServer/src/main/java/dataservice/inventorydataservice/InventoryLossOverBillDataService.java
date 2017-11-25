package main.java.dataservice.inventorydataservice;

import main.java.data.datautility.ResultMessage;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.inventorybill.InventoryLossOverBillPO;

public interface InventoryLossOverBillDataService {

    public InventoryLossOverBillPO find(BillQueryPO query);

    public ResultMessage insert(InventoryLossOverBillPO bill);

    public ResultMessage update(InventoryLossOverBillPO bill);


}
