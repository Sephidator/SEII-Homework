package client_presentationdriver.accountblservicedriver;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.accountblservice.AccountBlService;
import client_blservicestub.accountblservicestub.AccountBlServiceStub;
import org.junit.Test;
import po.UserPO;
import vo.AccountVO;
import static org.junit.Assert.*;

/**
 * Created by wangn on 2017.10.22.
 */
public class AccountBlServiceDriver {
    AccountBlService accountBlService = new AccountBlServiceStub();

    @Test
    public void isPrimeRight() throws Exception {
        assertEquals(accountBlService.isPrimeRight(new UserPO(0, 45, true,"wang","finance","1234",new  String[]{})), ResultMessage.success);
    }

    @Test
    public void addAccount() throws Exception {
        assertEquals(accountBlService.addAccount("6212262402017123456","wang"), ResultMessage.success);
    }

    @Test
    public void showAccount() throws Exception {
        assertEquals(accountBlService.showAccount("6212262402017123456").toString(), "[账号=6212262402017123456, 账号名称=wang, 账户余额=0.0, 可见状态=true]");
    }

    @Test
    public void getAccountID() throws Exception {
        assertEquals(accountBlService.getAccountID(new AccountVO("6212262402017123456","wang",0,true)),"6212262402017123456");
    }

    @Test
    public void getAccountName() throws Exception {
        assertEquals(accountBlService.getAccountName("6212262402017123456"),"wang");
    }

    @Test
    public void getAccountRem() throws Exception {
        assertEquals(accountBlService.getAccountRem("6212262402017123456"),0,0.1);
    }

    @Test
    public void searchAccount() throws Exception {
        assertEquals(accountBlService.searchAccount("wang").get(0).toString(),"[账号=6212262402017123456, 账号名称=wang, 账户余额=0.0, 可见状态=true]");
    }

}