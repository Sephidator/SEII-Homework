package main.java.dataservice.saledataservice;

import main.java.data.datautility.ResultMessage;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.salebill.SaleRefundBillPO;

import java.util.ArrayList;

public interface SaleRefundBillDataService {
    public ArrayList<SaleRefundBillPO> find(BillQueryPO query);
    public String getID();
    public ResultMessage insert(SaleRefundBillPO po);
    public ResultMessage update(SaleRefundBillPO po);
}
