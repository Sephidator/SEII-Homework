package main.java.dataservice.saledataservice;

import main.java.data.datautility.ResultMessage;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.salebill.SaleTradeBillPO;

import java.util.ArrayList;

public interface SaleTradeBillDataService {
    public ArrayList<SaleTradeBillPO> find(BillQueryPO query);
    public String getID();
    public ResultMessage insert(SaleTradeBillPO po);
    public ResultMessage update(SaleTradeBillPO po);
}
