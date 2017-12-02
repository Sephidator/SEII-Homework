package businesslogic_mock.accountblmock;

import businesslogic.accountbl.AccountTool;
import businesslogic.blutility.ResultMessage;
import vo.account.AccountQueryVO;
import vo.account.AccountVO;

import java.util.ArrayList;

public class AccoutToolMock implements AccountTool{
    @Override
    public ArrayList<AccountVO> getAccountList(AccountQueryVO query) {
        ArrayList<AccountVO> accountVOArrayList = new ArrayList<AccountVO>();
        AccountVO accountVO = new AccountVO();
        accountVOArrayList.add(accountVO);
        return accountVOArrayList;
    }

    @Override
    public ResultMessage editAccount(AccountVO account) {
        return ResultMessage.success;
    }
}
