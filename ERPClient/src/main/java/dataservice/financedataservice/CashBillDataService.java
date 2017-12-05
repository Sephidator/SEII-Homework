package main.java.dataservice.financedataservice;

import main.java.po.bill.BillQueryPO;
import main.java.po.bill.financebill.CashBillPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface CashBillDataService extends Remote {
    /*根据筛选条件获取现金费用单列表*/
    ArrayList<CashBillPO> finds(BillQueryPO query) throws RemoteException;

    /*添加现金费用单*/
    String insert(CashBillPO po);

    /*更新现金费用单信息*/
    void update(CashBillPO po);
}
