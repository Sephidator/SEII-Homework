package server_dataservicestub.editbilldataservicestub;

import dataservice.editbilldataservice.EditInventoryBillDataService;
import po.InventoryBillPO;

import java.util.ArrayList;

public class EditInventoryBillDataServiceStub implements EditInventoryBillDataService{
    @Override
    public ArrayList<InventoryBillPO> getInventoryBill(int state) {
        ArrayList<InventoryBillPO> list=new ArrayList<>();
        list.add(new InventoryBillPO());
        list.add(new InventoryBillPO());
        return list;
    }
}
