package dataservice.financedataservice;

import data.datautility.ResultMessage;
import po.bill.BillQueryPO;
import po.bill.financebill.CashBillPO;

import java.util.ArrayList;

public interface CashBillDataService {

    /*根据筛选条件获取现金费用单列表*/
    public ArrayList<CashBillPO> find(BillQueryPO query);

    /*得到新的现金费用单ID*/
    public String getID();

    /*添加现金费用单*/
    public ResultMessage insert(CashBillPO po);

    /*更新现金费用单信息*/
    public ResultMessage update(CashBillPO po);
}
