package main.java.dataservice.financedataservice;

import main.java.data.datautility.ResultMessage;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.financebill.ReceiptBillPO;

import java.util.ArrayList;

/**
 * Created by wangn on 2017.11.06.
 */
public interface ReceiptBillDataService {

    /*根据筛选条件获取收款单列表*/
    public ArrayList<ReceiptBillPO> find(BillQueryPO query);

    /*得到新的收款单ID*/
    public String getID();

    /*添加收款单*/
    public ResultMessage insert(ReceiptBillPO po);

    /*更新收款单信息*/
    public ResultMessage update(ReceiptBillPO po);
}
