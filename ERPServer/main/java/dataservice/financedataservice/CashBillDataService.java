package dataservice.financedataservice;

import po.CashBillPO;
import businesslogic.blutility.ResultMessage;

import java.rmi.RemoteException;

/**
 * Created by wangn on 2017.10.21.
 */
public interface CashBillDataService {

    /*上传一个po*/
    public ResultMessage submitDoc(CashBillPO po) throws RemoteException;

    /*保存一个po*/
    public ResultMessage saveDoc(CashBillPO po) throws RemoteException;

    /*根据现金费用款单ID查找并返回相应的CashBillPO结果*/
    public CashBillPO find(String docID) throws RemoteException;

    /*在持久化数据中插入一个po记录*/
    public ResultMessage insert(CashBillPO po) throws RemoteException;

    /*更新一个po*/
    public ResultMessage update(CashBillPO po) throws RemoteException;

    /*	初始化持久化数据库*/
    public ResultMessage init() throws RemoteException;

    /*结束持久化数据库*/
    public ResultMessage finish() throws RemoteException;
}
