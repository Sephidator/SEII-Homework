package dataservice.financedataservice;

import po.ReceiptBillPO;

import java.rmi.RemoteException;
import businesslogic.blutility.ResultMessage;

/**
 * Created by wangn on 2017.10.21.
 */
public interface ReceiptBillDataService {

    /*�ϴ�һ��po*/
    public ResultMessage submitDoc(ReceiptBillPO po) throws RemoteException;

    /*����һ��po*/
    public ResultMessage saveDoc(ReceiptBillPO po) throws RemoteException;

    /*�����տID���Ҳ�������Ӧ��ReceiptBillPO���*/
    public ReceiptBillPO find(String docID) throws RemoteException;

    /*�ڳ־û������в���һ��po��¼*/
    public ResultMessage insert(ReceiptBillPO po) throws RemoteException;

    /*����һ��po*/
    public ResultMessage update(ReceiptBillPO po) throws RemoteException;

    /*	��ʼ���־û����ݿ�*/
    public ResultMessage init() throws RemoteException;

    /*�����־û����ݿ�*/
    public ResultMessage finish() throws RemoteException;
}
