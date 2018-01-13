package test.java.unit_test.accountbltest;

import main.java.businesslogicfactory.accountblfactory.AccountBlFactory;
import main.java.businesslogicservice.accountblservice.AccountBlService;
import main.java.client_blservicestub.accountblservicestub.AccountBlServiceStub;
import main.java.vo.account.AccountVO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountBlServiceTest {
    AccountBlService service= AccountBlFactory.getService();

    @Test
    public void getAccountList() throws Exception {
        assertEquals(true,service.getAccountList(null).size()>=0);
    }

    @Test
    public void addAccount() throws Exception {
        assertEquals("Account",service.addAccount(new AccountVO()).substring(0,7));
    }

}