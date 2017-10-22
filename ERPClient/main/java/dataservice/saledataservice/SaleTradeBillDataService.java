package dataservice.saledataservice;

import businesslogic.blutility.ResultMessage;
import po.SaleTradeBillPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface SaleTradeBillDataService {
    public ResultMessage insert(SaleTradeBillPO po)throws RemoteException;
    public SaleTradeBillPO find(String ID) throws RemoteException;
    public ResultMessage delete(SaleTradeBillPO po) throws RemoteException;
    public ResultMessage update(SaleTradeBillPO po) throws RemoteException;
    public String getID() throws RemoteException;
    public ArrayList<SaleTradeBillPO> getList() throws RemoteException;
}
