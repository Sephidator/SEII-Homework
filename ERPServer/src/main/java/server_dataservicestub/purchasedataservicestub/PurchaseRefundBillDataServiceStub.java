package main.java.server_dataservicestub.purchasedataservicestub;

import main.java.data.datautility.ResultMessage;
import main.java.dataservice.purchasedataservice.PurchaseRefundBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.purchasebill.PurchaseRefundBillPO;

import java.util.ArrayList;

public class PurchaseRefundBillDataServiceStub implements PurchaseRefundBillDataService{
    @Override
    public ArrayList<PurchaseRefundBillPO> find(BillQueryPO query) {
        ArrayList<PurchaseRefundBillPO> list=new ArrayList<>();
        list.add(new PurchaseRefundBillPO());
        return list;
    }

    @Override
    public String getID() {
        return "123";
    }

    @Override
    public ResultMessage insert(PurchaseRefundBillPO po) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(PurchaseRefundBillPO po) {
        return ResultMessage.success;
    }
}
