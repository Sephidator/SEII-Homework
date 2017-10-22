package dataservice.purchasedataservice;

import data.datautility.ResultMessage;
import po.PurchaseRefundBillPO;
import po.PurchaseTradeBillPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface PurchaseRefundBillDataService {
    public ResultMessage insert(PurchaseRefundBillPO po)throws RemoteException;
    public PurchaseRefundBillPO find(String ID) throws RemoteException;
    public ResultMessage delete(PurchaseRefundBillPO po) throws RemoteException;
    public ResultMessage update(PurchaseRefundBillPO po) throws RemoteException;
    public String getID() throws RemoteException;
    public ArrayList<PurchaseRefundBillPO> getList() throws RemoteException;
}
