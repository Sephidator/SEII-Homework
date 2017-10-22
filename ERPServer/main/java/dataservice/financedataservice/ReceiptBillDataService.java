package dataservice.financedataservice;

import po.ReceiptBillPO;

import java.rmi.RemoteException;
import businesslogic.blutility.ResultMessage;

/**
 * Created by wangn on 2017.10.21.
 */
public interface ReceiptBillDataService {

    /*上传一个po*/
    public ResultMessage submitDoc(ReceiptBillPO po) throws RemoteException;

    /*保存一个po*/
    public ResultMessage saveDoc(ReceiptBillPO po) throws RemoteException;

    /*根据收款单ID查找并返回相应的ReceiptBillPO结果*/
    public ReceiptBillPO find(String docID) throws RemoteException;

    /*在持久化数据中插入一个po记录*/
    public ResultMessage insert(ReceiptBillPO po) throws RemoteException;

    /*更新一个po*/
    public ResultMessage update(ReceiptBillPO po) throws RemoteException;

    /*	初始化持久化数据库*/
    public ResultMessage init() throws RemoteException;

    /*结束持久化数据库*/
    public ResultMessage finish() throws RemoteException;
}
