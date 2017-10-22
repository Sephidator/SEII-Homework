package dataservice.financedataservice;

import po.CashBillPO;
import businesslogic.blutility.ResultMessage;

import java.rmi.RemoteException;

/**
 * Created by wangn on 2017.10.21.
 */
public interface CashBillDataService {

    /*�ϴ�һ��po*/
    public ResultMessage submitDoc(CashBillPO po) throws RemoteException;

    /*����һ��po*/
    public ResultMessage saveDoc(CashBillPO po) throws RemoteException;

    /*�����ֽ���ÿID���Ҳ�������Ӧ��CashBillPO���*/
    public CashBillPO find(String docID) throws RemoteException;

    /*�ڳ־û������в���һ��po��¼*/
    public ResultMessage insert(CashBillPO po) throws RemoteException;

    /*����һ��po*/
    public ResultMessage update(CashBillPO po) throws RemoteException;

    /*	��ʼ���־û����ݿ�*/
    public ResultMessage init() throws RemoteException;

    /*�����־û����ݿ�*/
    public ResultMessage finish() throws RemoteException;
}
