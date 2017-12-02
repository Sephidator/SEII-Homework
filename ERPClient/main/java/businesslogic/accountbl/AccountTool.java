package businesslogic.accountbl;

import businesslogic.blutility.ResultMessage;
import vo.account.AccountQueryVO;
import vo.account.AccountVO;

import java.util.ArrayList;

public interface AccountTool {

    public ArrayList<AccountVO> getAccountList(AccountQueryVO query);//返回账户列表

    public ResultMessage editAccount(AccountVO account);//编辑一个账户，更新账户列表
}
