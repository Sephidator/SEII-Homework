package businesslogicservice.accountblservice;
import po.UserPO;//TODO 完成包的路径
import vo.AccountVO;

import java.util.ArrayList;

/**
 * 账户管理涉及账户的增删改查，
 * Created by wangn on 2017.10.18.
 */
public interface AccountBlService {

    /******************************/
    public enum ResultMessage{True, False, Success};
    /*****************************/

    /*验证用户的权限情况，最高权限返回true*/
    public ResultMessage isPrimeRight(UserPO po);

    /*增添新账户，成功返回true*/
    public ResultMessage addAccount(String accountID, String name);

    /*查看一个没有被删除的账户详细信息，打开详情界面AccountDetailBlService*/
    public AccountVO showAccount(String accountID);

    /*获取点击账户的账户编号，需要传入对象*/
    public String getAccountID(AccountVO vo);

    /*获取点击账户的账户名称，需要传入对象*/
    public String getAccountName(String accountID);

    /*获取点击账户的账户余额，需要传入对象*/
    public double getAccountRem(String accountID);

    /*输入关键字，返回模糊查找匹配的账户的ID*/
    public ArrayList<AccountVO> searchAccount(String keyWords);

}
