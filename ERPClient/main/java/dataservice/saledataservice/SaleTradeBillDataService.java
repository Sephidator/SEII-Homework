package dataservice.saledataservice;

import businesslogic.blutility.ResultMessage;
import po.bill.BillQueryPO;
import po.bill.salebill.SaleTradeBillPO;

import java.util.ArrayList;

public interface SaleTradeBillDataService {
    public ArrayList<SaleTradeBillPO> find(BillQueryPO query);
    public String getID();
    public ResultMessage insert(SaleTradeBillPO po);
    public ResultMessage update(SaleTradeBillPO po);
}
