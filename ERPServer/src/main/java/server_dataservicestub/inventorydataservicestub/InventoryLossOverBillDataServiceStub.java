package main.java.server_dataservicestub.inventorydataservicestub;

import main.java.data.datautility.ResultMessage;
import main.java.dataservice.inventorydataservice.InventoryLossOverBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.inventorybill.InventoryLossOverBillPO;

public class InventoryLossOverBillDataServiceStub implements InventoryLossOverBillDataService {
    @Override
    public InventoryLossOverBillPO find(BillQueryPO query) {
        InventoryLossOverBillPO inventoryLossOverBillPO=new InventoryLossOverBillPO();
        inventoryLossOverBillPO.setComment("1");
        return inventoryLossOverBillPO;
    }

    @Override
    public ResultMessage insert(InventoryLossOverBillPO bill) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(InventoryLossOverBillPO bill) {
        return ResultMessage.success;
    }
}
