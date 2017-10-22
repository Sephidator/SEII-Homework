package businesslogicservice.accountblservice;
import po.UserPO;//TODO ��ɰ���·��
import vo.AccountVO;

import java.util.ArrayList;

/**
 * �˻������漰�˻�����ɾ�Ĳ飬
 * Created by wangn on 2017.10.18.
 */
public interface AccountBlService {

    /******************************/
    public enum ResultMessage{True, False, Success};
    /*****************************/

    /*��֤�û���Ȩ����������Ȩ�޷���true*/
    public ResultMessage isPrimeRight(UserPO po);

    /*�������˻����ɹ�����true*/
    public ResultMessage addAccount(String accountID, String name);

    /*�鿴һ��û�б�ɾ�����˻���ϸ��Ϣ�����������AccountDetailBlService*/
    public AccountVO showAccount(String accountID);

    /*��ȡ����˻����˻���ţ���Ҫ�������*/
    public String getAccountID(AccountVO vo);

    /*��ȡ����˻����˻����ƣ���Ҫ�������*/
    public String getAccountName(String accountID);

    /*��ȡ����˻����˻�����Ҫ�������*/
    public double getAccountRem(String accountID);

    /*����ؼ��֣�����ģ������ƥ����˻���ID*/
    public ArrayList<AccountVO> searchAccount(String keyWords);

}
