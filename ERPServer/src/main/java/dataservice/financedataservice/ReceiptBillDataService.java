package main.java.dataservice.financedataservice;

import main.java.po.bill.financebill.ReceiptBillPO;
import main.java.po.bill.BillQueryPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ReceiptBillDataService extends Remote {

    /*根据筛选条件获取收款单列表*/
    ArrayList<ReceiptBillPO> finds(BillQueryPO query) throws RemoteException;

    /*添加收款单*/
    String insert(ReceiptBillPO po) throws RemoteException;

    /*更新收款单信息*/
    void update(ReceiptBillPO po) throws RemoteException;
}
