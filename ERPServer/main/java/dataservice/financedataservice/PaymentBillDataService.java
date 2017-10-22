package dataservice.financedataservice;


import java.rmi.RemoteException;
import po.PaymentBillPO;
import businesslogic.blutility.ResultMessage;

/**
 * Created by wangn on 2017.10.21.
 */
public interface PaymentBillDataService {

    /*�ϴ�һ��po*/
    public ResultMessage submitDoc(PaymentBillPO po) throws RemoteException;

    /*����һ��po*/
    public ResultMessage saveDoc(PaymentBillPO po) throws RemoteException;

    /*���ݸ��ID���Ҳ�������Ӧ��PaymentBillPO���*/
    public PaymentBillPO find(String docID) throws RemoteException;

    /*�ڳ־û������в���һ��po��¼*/
    public ResultMessage insert(PaymentBillPO po) throws RemoteException;

    /*����һ��po*/
    public ResultMessage update(PaymentBillPO po) throws RemoteException;

    /*	��ʼ���־û����ݿ�*/
    public ResultMessage init() throws RemoteException;

    /*�����־û����ݿ�*/
    public ResultMessage finish() throws RemoteException;

}
