package main.java.server_dataservicestub.saledataservicestub;

import main.java.data.datautility.ResultMessage;
import main.java.dataservice.saledataservice.SaleRefundBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.salebill.SaleRefundBillPO;
import main.java.po.bill.salebill.SaleTradeBillQueryPO;

import java.util.ArrayList;

public class SaleRefundBillDataServiceStub implements SaleRefundBillDataService {
    @Override
    public ArrayList<SaleRefundBillPO> find(BillQueryPO query) {
        ArrayList<SaleRefundBillPO> list = new ArrayList<>();
        list.add(new SaleRefundBillPO());
        return list;
    }

    @Override
    public ArrayList<SaleRefundBillPO> find(SaleTradeBillQueryPO query) {
        ArrayList<SaleRefundBillPO> list = new ArrayList<>();
        list.add(new SaleRefundBillPO());
        return list;
    }

    @Override
    public ResultMessage insert(SaleRefundBillPO po) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(SaleRefundBillPO po) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public String getSaleRefundBillID() {
        return "XSTHD-20170910-12345";
    }
}