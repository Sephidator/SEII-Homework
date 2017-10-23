package dataservice.editbilldataservice;

import po.FinanceBillPO;
import po.InventoryBillPO;

import java.util.ArrayList;

public interface EditFinanceBillDataService {
    public ArrayList<FinanceBillPO> getFinanceBill(int state);
}
