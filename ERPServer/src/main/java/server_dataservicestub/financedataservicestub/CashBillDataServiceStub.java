package main.java.server_dataservicestub.financedataservicestub;

import main.java.data.datautility.ResultMessage;
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
    public ResultMessage insert(CashBillPO po) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(CashBillPO po) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public String getCashBillID() {
        return "XJFYD-20171106-00001";
    }
}
