package dataservice.accountdataservice;

import java.rmi.RemoteException;
import po.UserPO;
import po.AccountPO;
import businesslogic.blutility.ResultMessage;


/**
 * Created by wangn on 2017.10.21.
 */
public interface AccountDataService {


    /*�����û��Ƿ�Ϊ���Ȩ��*/
    public String rightControl(UserPO po) throws RemoteException;

    /*�����˻�ID���Ҳ�������Ӧ��AccountPO*/
    public AccountPO find(String accountID) throws RemoteException;

    /*�ڳ־û������в���һ��po��¼*/
    public ResultMessage insert(AccountPO po) throws RemoteException;

    /*ɾ��һ��po*/
    public ResultMessage delete(AccountPO po) throws RemoteException;

    /*����һ��po*/
    public ResultMessage update(AccountPO po) throws RemoteException;

    /*��ʼ���־û����ݿ�*/
    public ResultMessage init() throws RemoteException;

    /*�����־û����ݿ�*/
    public ResultMessage finish() throws RemoteException;

}
