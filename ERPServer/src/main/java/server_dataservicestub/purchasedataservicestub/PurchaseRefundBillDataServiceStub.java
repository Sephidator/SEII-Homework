package server_dataservicestub.purchasedataservicestub;

import data.datautility.ResultMessage;
import dataservice.purchasedataservice.PurchaseRefundBillDataService;
import po.bill.BillQueryPO;
import po.bill.purchasebill.PurchaseRefundBillPO;

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
