package server_dataservicestub.editbilldataservicestub;

import dataservice.editbilldataservice.EditFinanceBillDataService;
import dataservice.editbilldataservice.EditInventoryBillDataService;
import po.FinanceBillPO;
import po.InventoryBillPO;

import java.util.ArrayList;

public class EditFinanceBillDataServiceStub implements EditFinanceBillDataService {
    @Override
    public ArrayList<FinanceBillPO> getFinanceBill(int state) {
        ArrayList<FinanceBillPO> list=new ArrayList<>();
        list.add(new FinanceBillPO());
        list.add(new FinanceBillPO());
        return list;
    }
}
