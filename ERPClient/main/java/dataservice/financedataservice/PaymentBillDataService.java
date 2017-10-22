package dataservice.financedataservice;


import java.rmi.RemoteException;


import businesslogic.blutility.ResultMessage;
import po.PaymentBillPO;
/**
 * Created by wangn on 2017.10.21.
 */
public interface PaymentBillDataService {

    /*上传一个po*/
    public ResultMessage submitDoc(PaymentBillPO po) throws RemoteException;

    /*保存一个po*/
    public ResultMessage saveDoc(PaymentBillPO po) throws RemoteException;

    /*根据付款单ID查找并返回相应的PaymentBillPO结果*/
    public PaymentBillPO find(String docID) throws RemoteException;

    /*在持久化数据中插入一个po记录*/
    public ResultMessage insert(PaymentBillPO po) throws RemoteException;

    /*更新一个po*/
    public ResultMessage update(PaymentBillPO po) throws RemoteException;

    /*	初始化持久化数据库*/
    public ResultMessage init() throws RemoteException;

    /*结束持久化数据库*/
    public ResultMessage finish() throws RemoteException;

}
