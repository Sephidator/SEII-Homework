package businesslogicservice.accountblservice;

/**
 * �˻�������ʾ�˻������ԣ��ṩ�Ը��˻����޸�Ȩ��
 * Created by wangn on 2017.10.18.
 */
public interface AccountInfoBlService {

    /******************************/
    public enum ResultMessage{True, False, Success};
    /*****************************/

    /*�޸��˻����ԣ��ɹ�����true*/
    public ResultMessage mockAccountName(String accountID, String newName);

    /*ɾ���˻����ɹ�����true*/
    public ResultMessage delAccount(String accountID);

    /*�����˻��������б������޸��˻�����ɾ���˻��Ժ�ˢ�½���*/
    public ResultMessage updateAccount();
}
