package server_dataservicestub.saledataservicestub;

import data.datautility.ResultMessage;
import dataservice.saledataservice.SaleRefundBillDataService;
import po.bill.BillQueryPO;
import po.bill.salebill.SaleRefundBillPO;

import java.util.ArrayList;

public class SaleRefundBillDataServiceStub implements SaleRefundBillDataService{
    @Override
    public ArrayList<SaleRefundBillPO> find(BillQueryPO query) {
        ArrayList<SaleRefundBillPO> list=new ArrayList<>();
        list.add(new SaleRefundBillPO());
        return list;
    }

    @Override
    public String getID() {
        return "123";
    }

    @Override
    public ResultMessage insert(SaleRefundBillPO po) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(SaleRefundBillPO po) {
        return ResultMessage.success;
    }
}