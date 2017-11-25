package main.java.server_dataservicestub.financedataservicestub;

import main.java.data.datautility.ResultMessage;
import main.java.dataservice.financedataservice.CashBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.financebill.CashBillPO;
import main.java.po.bill.financebill.CashItemPO;

import java.util.Date;
import java.util.ArrayList;

public class CashBillDataServiceStub implements CashBillDataService{
    @Override
    public ArrayList<CashBillPO> find(BillQueryPO query) {
        ArrayList<CashBillPO> cashBillPOArrayList = new ArrayList<CashBillPO>();
        CashBillPO cashBillPO = new CashBillPO("XJFYD-20171106-00001","草稿",new Date(),
                "现金费用单","Fiannce-20171106-00001","",0,new ArrayList<CashItemPO>());
        cashBillPOArrayList.add(cashBillPO);
        return cashBillPOArrayList;
    }

    @Override
    public String getID() {
        return "XJFYD-20171106-00001";
    }

    @Override
    public ResultMessage insert(CashBillPO po) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(CashBillPO po) {
        return ResultMessage.success;
    }
}
