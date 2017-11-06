package dataservice.purchasedataservice;

import data.datautility.ResultMessage;
import po.bill.BillQueryPO;
import po.bill.purchasebill.PurchaseRefundBillPO;

import java.util.ArrayList;

public interface PurchaseRefundBillDataService {
    public ArrayList<PurchaseRefundBillPO> find(BillQueryPO query);
    public String getID();
    public ResultMessage insert(PurchaseRefundBillPO po);
    public ResultMessage update(PurchaseRefundBillPO po);
}
