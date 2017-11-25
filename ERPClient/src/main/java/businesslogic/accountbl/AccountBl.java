package main.java.businesslogic.accountbl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.accountblservice.AccountBlService;
import main.java.vo.account.AccountQueryVO;
import main.java.vo.account.AccountVO;

import java.util.ArrayList;

public class AccountBl implements AccountBlService,AccountTool{

    @Override
    public ArrayList<AccountVO> getAccountList(AccountQueryVO query) {
        return null;
    }

    @Override
    public ResultMessage editAccount(AccountVO account) {
        return null;
    }

    @Override
    public String getID() {
        return null;
    }

    @Override
    public ResultMessage addAccount(AccountVO account) {
        return null;
    }

    @Override
    public ResultMessage deleteAccount(AccountVO account) {
        return null;
    }
}
