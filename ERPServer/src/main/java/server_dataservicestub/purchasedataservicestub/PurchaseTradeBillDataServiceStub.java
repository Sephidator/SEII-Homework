package server_dataservicestub.purchasedataservicestub;

import data.datautility.ResultMessage;
import dataservice.purchasedataservice.PurchaseTradeBillDataService;
import po.bill.BillQueryPO;
import po.bill.purchasebill.PurchaseTradeBillPO;

import java.util.ArrayList;

public class PurchaseTradeBillDataServiceStub implements PurchaseTradeBillDataService{
    @Override
    public ArrayList<PurchaseTradeBillPO> find(BillQueryPO query) {
        ArrayList<PurchaseTradeBillPO> list=new ArrayList<>();
        list.add(new PurchaseTradeBillPO());
        return list;
    }

    @Override
    public String getID() {
        return "123";
    }

    @Override
    public ResultMessage insert(PurchaseTradeBillPO po) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(PurchaseTradeBillPO po) {
        return ResultMessage.success;
    }
}

