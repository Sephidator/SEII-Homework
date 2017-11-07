package server_dataservicestub.saledataservicestub;

import data.datautility.ResultMessage;
import dataservice.saledataservice.SaleTradeBillDataService;
import po.bill.BillQueryPO;
import po.bill.salebill.SaleTradeBillPO;

import java.util.ArrayList;

public class SaleTradeBillDataServiceStub implements SaleTradeBillDataService{
    @Override
    public ArrayList<SaleTradeBillPO> find(BillQueryPO query) {
        ArrayList<SaleTradeBillPO> list=new ArrayList<>();
        list.add(new SaleTradeBillPO());
        return list;
    }

    @Override
    public String getID() {
        return "123";
    }

    @Override
    public ResultMessage insert(SaleTradeBillPO po) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(SaleTradeBillPO po) {
        return ResultMessage.success;
    }
}
