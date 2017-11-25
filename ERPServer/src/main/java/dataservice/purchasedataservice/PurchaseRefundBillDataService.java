package main.java.dataservice.purchasedataservice;

import main.java.data.datautility.ResultMessage;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.purchasebill.PurchaseRefundBillPO;

import java.util.ArrayList;

public interface PurchaseRefundBillDataService {
    public ArrayList<PurchaseRefundBillPO> find(BillQueryPO query);
    public String getID();
    public ResultMessage insert(PurchaseRefundBillPO po);
    public ResultMessage update(PurchaseRefundBillPO po);
}
