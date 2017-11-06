package dataservice.financedataservice;

import businesslogic.blutility.ResultMessage;
import po.bill.BillQueryPO;
import po.bill.financebill.PaymentBillPO;

import java.util.ArrayList;

public interface PaymentBillDataService {

    /*根据筛选条件获取付款单列表*/
    public ArrayList<PaymentBillPO> find(BillQueryPO query);

    /*得到新的付款单ID*/
    public String getID();

    /*添加付款单*/
    public ResultMessage insert(PaymentBillPO po);

    /*更新付款单信息*/
    public ResultMessage update(PaymentBillPO po);
}
