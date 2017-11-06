package businesslogicservice.accountblservice;

import businesslogic.blutility.ResultMessage;
import vo.account.AccountQueryVO;
import vo.account.AccountVO;

import java.util.ArrayList;

public interface AccountBlService {

    public String getID();//返回账户ID

    public ResultMessage addAccount(AccountVO account);//添加一个账户，更新账户列表

    public ResultMessage deleteAccount(AccountVO account);//删除一个账户，更新账户列表

}
