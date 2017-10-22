package dataservice.logdataservice;

import po.LogPO;

import java.rmi.RemoteException;
import businesslogic.blutility.ResultMessage;

/**
 * Created by wangn on 2017.10.21.
 */
public interface LogDataService {

    /*�������year���Ҳ�������Ӧ��LogPO���*/
    public LogPO find(int year) throws RemoteException;

    /*�ڳ־û������в���һ��po��¼*/
    public ResultMessage insert(LogPO po) throws RemoteException;

    /*ɾ��һ��po*/
    public ResultMessage delete(LogPO po) throws RemoteException;

    /*����һ��po*/
    public ResultMessage update(LogPO po) throws RemoteException;

    /*��ʼ���־û����ݿ�*/
    public ResultMessage init() throws RemoteException;

    /*�����־û����ݿ�*/
    public ResultMessage finish() throws RemoteException;
}
