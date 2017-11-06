package dataservice.saledataservice;

import data.datautility.ResultMessage;
import po.bill.BillQueryPO;
import po.bill.salebill.SaleRefundBillPO;

import java.util.ArrayList;

public interface SaleRefundBillDataService {
    public ArrayList<SaleRefundBillPO> find(BillQueryPO query);
    public String getID();
    public ResultMessage insert(SaleRefundBillPO po);
    public ResultMessage update(SaleRefundBillPO po);
}
