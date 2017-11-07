package server_dataservicestub.inventorydataservicestub;

import data.datautility.ResultMessage;
import dataservice.inventorydataservice.InventoryGiftBillDataService;
import po.bill.BillQueryPO;
import po.bill.inventorybill.InventoryGiftBillPO;

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
