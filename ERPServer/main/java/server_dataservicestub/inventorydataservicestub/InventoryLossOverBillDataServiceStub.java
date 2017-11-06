package server_dataservicestub.inventorydataservicestub;

import data.datautility.ResultMessage;
import dataservice.inventorydataservice.InventoryLossOverBillDataService;
import po.bill.BillQueryPO;
import po.bill.inventorybill.InventoryLossOverBillPO;

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
