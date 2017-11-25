package main.java.dataservice.purchasedataservice;

import main.java.data.datautility.ResultMessage;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.purchasebill.PurchaseTradeBillPO;

import java.util.ArrayList;

public interface PurchaseTradeBillDataService {
    public ArrayList<PurchaseTradeBillPO> find(BillQueryPO query);
    public String getID();
    public ResultMessage insert(PurchaseTradeBillPO po);
    public ResultMessage update(PurchaseTradeBillPO po);
}
