package main.java.dataservice.financedataservice;

import main.java.po.bill.BillQueryPO;
import main.java.po.bill.financebill.PaymentBillPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface PaymentBillDataService extends Remote {

    /*根据筛选条件获取付款单列表*/
    ArrayList<PaymentBillPO> finds(BillQueryPO query) throws RemoteException;

    /*添加付款单*/
    String insert(PaymentBillPO po) throws RemoteException;

    /*更新付款单信息*/
    void update(PaymentBillPO po) throws RemoteException;
}
