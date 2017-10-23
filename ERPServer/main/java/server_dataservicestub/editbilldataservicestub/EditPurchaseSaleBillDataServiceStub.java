package server_dataservicestub.editbilldataservicestub;

import dataservice.editbilldataservice.EditFinanceBillDataService;
import dataservice.editbilldataservice.EditPurchaseSaleBillDataService;
import po.FinanceBillPO;
import po.PurchaseBillPO;
import po.SaleBillPO;

import java.util.ArrayList;

public class EditPurchaseSaleBillDataServiceStub implements EditPurchaseSaleBillDataService {
    @Override
    public ArrayList<PurchaseBillPO> getPurchaseBill(int state) {
        ArrayList<PurchaseBillPO> list=new ArrayList<>();
        list.add(new PurchaseBillPO());
        list.add(new PurchaseBillPO());
        return list;
    }

    @Override
    public ArrayList<SaleBillPO> getSaleBill(int state) {
        ArrayList<SaleBillPO> list=new ArrayList<>();
        list.add(new SaleBillPO());
        list.add(new SaleBillPO());
        return list;
    }
}
