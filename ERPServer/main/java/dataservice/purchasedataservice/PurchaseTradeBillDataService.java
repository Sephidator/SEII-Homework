package dataservice.purchasedataservice;

import data.datautility.ResultMessage;
import po.bill.BillQueryPO;
import po.bill.purchasebill.PurchaseTradeBillPO;

import java.util.ArrayList;

public interface PurchaseTradeBillDataService {
    public ArrayList<PurchaseTradeBillPO> find(BillQueryPO query);
    public String getID();
    public ResultMessage insert(PurchaseTradeBillPO po);
    public ResultMessage update(PurchaseTradeBillPO po);
}
