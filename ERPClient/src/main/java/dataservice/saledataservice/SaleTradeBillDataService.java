package main.java.dataservice.saledataservice;

import main.java.po.bill.BillQueryPO;
import main.java.po.bill.salebill.SaleTradeBillPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface SaleTradeBillDataService extends Remote {
    ArrayList<SaleTradeBillPO> find(BillQueryPO query) throws RemoteException;

    String insert(SaleTradeBillPO po) throws RemoteException;

    void update(SaleTradeBillPO po) throws RemoteException;
}
