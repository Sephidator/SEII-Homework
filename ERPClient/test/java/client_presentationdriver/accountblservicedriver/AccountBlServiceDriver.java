package client_presentationdriver.accountblservicedriver;

import businesslogicservice.accountblservice.AccountBlService;
import client_blservicestub.accountblservicestub.AccountBlServiceStub;
import po.UserPO;
import vo.AccountVO;

import static org.junit.Assert.*;

/**
 * Created by wangn on 2017.10.22.
 */
public class AccountBlServiceDriver {
    AccountBlService accountBlService = new AccountBlServiceStub();
    @org.junit.Test
    public void isPrimeRight() throws Exception {
        assertEquals(accountBlService.isPrimeRight(new UserPO(0, 45, true,"wang","finance","1234",new  String[]{})), AccountBlService.ResultMessage.Success);
    }

    @org.junit.Test
    public void addAccount() throws Exception {
        assertEquals(accountBlService.addAccount("6212262402017123456","wang"), AccountBlService.ResultMessage.Success);
    }

    @org.junit.Test
    public void showAccount() throws Exception {
        assertEquals(accountBlService.showAccount("6212262402017123456").toString(), "[账号=6212262402017123456, 账号名称=wang, 账户余额=0, 可见状态=true]");
    }

    @org.junit.Test
    public void getAccountID() throws Exception {
        assertEquals(accountBlService.getAccountID(new AccountVO("6212262402017123456","wang",0)),"6212262402017123456");
    }

    @org.junit.Test
    public void getAccountName() throws Exception {
        assertEquals(accountBlService.getAccountName("6212262402017123456"),"wang");
    }

    @org.junit.Test
    public void getAccountRem() throws Exception {
        assertEquals(accountBlService.getAccountRem("6212262402017123456"),0);
    }

    @org.junit.Test
    public void searchAccount() throws Exception {
        assertEquals(accountBlService.searchAccount("wang").get(0).toString(),"[账号=6212262402017123456, 账号名称=wang, 账户余额=0, 可见状态=true]");
    }

}