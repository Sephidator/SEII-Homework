package main.java.server_dataservicestub.financedataservicestub;

import main.java.dataservice.financedataservice.CashBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.financebill.CashBillPO;

import java.util.ArrayList;

public class CashBillDataServiceStub implements CashBillDataService {
    @Override
    public ArrayList<CashBillPO> find(BillQueryPO query) {
        ArrayList<CashBillPO> cashBillPOArrayList = new ArrayList<>();
        CashBillPO cashBillPO = new CashBillPO();
        cashBillPOArrayList.add(cashBillPO);
        return cashBillPOArrayList;
    }

    @Override
    public String insert(CashBillPO po) {
        return "XJFYD-20171212-12345";
    }

    @Override
    public void update(CashBillPO po) {

    }


}
