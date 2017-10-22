package dataservice.saledataservice;

import businesslogic.blutility.ResultMessage;
import po.SaleRefundBillPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface SaleRefundBillDataService {
    public ResultMessage insert(SaleRefundBillPO po)throws RemoteException;
    public SaleRefundBillPO find(String ID) throws RemoteException;
    public ResultMessage delete(SaleRefundBillPO po) throws RemoteException;
    public ResultMessage update(SaleRefundBillPO po) throws RemoteException;
    public String getID() throws RemoteException;
    public ArrayList<SaleRefundBillPO> getList() throws RemoteException;
}
