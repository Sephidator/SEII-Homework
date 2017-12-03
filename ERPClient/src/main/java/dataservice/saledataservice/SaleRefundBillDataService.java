package main.java.dataservice.saledataservice;

import main.java.po.bill.BillQueryPO;
import main.java.po.bill.salebill.SaleRefundBillPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface SaleRefundBillDataService extends Remote {

    ArrayList<SaleRefundBillPO> find(BillQueryPO query) throws RemoteException;

    String insert(SaleRefundBillPO po) throws RemoteException;

    String update(SaleRefundBillPO po) throws RemoteException;
}
