package main.java.client_blservicestub.accountblservicestub;

import main.java.businesslogicservice.accountblservice.AccountBlService;
import main.java.vo.account.AccountQueryVO;
import main.java.vo.account.AccountVO;

import java.util.ArrayList;

public class AccountBlServiceStub implements AccountBlService {

    @Override
    public ArrayList<AccountVO> getAccountList(AccountQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public String addAccount(AccountVO account) throws Exception {
        return "";
    }

    @Override
    public void deleteAccount(String accountID) throws Exception {

    }

    @Override
    public void editAccount(AccountVO account) throws Exception {

    }
}
