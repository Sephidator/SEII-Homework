package main.java.server_dataservicestub.inventorydataservicestub;

import main.java.data.datautility.ResultMessage;
import main.java.dataservice.inventorydataservice.InventoryGiftBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.inventorybill.InventoryGiftBillPO;

public class InventoryGiftBillDataServiceStub implements InventoryGiftBillDataService {
    @Override
    public InventoryGiftBillPO find(BillQueryPO query) {
        InventoryGiftBillPO inventoryGiftBillPO=new InventoryGiftBillPO();
        inventoryGiftBillPO.setClient(null);
        return inventoryGiftBillPO;
    }

    @Override
    public ResultMessage insert(InventoryGiftBillPO bill) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(InventoryGiftBillPO bill) {
        return ResultMessage.success;
    }
}
