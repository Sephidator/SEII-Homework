package test.java.client_presentationdriver.accountblservicedriver;

import main.java.businesslogicservice.accountblservice.AccountBlService;
import main.java.client_blservicestub.accountblservicestub.AccountBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountBlServiceDriver {
    AccountBlService service=new AccountBlServiceStub();

    @Test
    public void getAccountList() throws Exception {
        assertEquals(0,service.getAccountList(null).size());
    }

    @Test
    public void addAccount() throws Exception {
        assertEquals("",service.addAccount(null));
    }

    @Test
    public void deleteAccount() throws Exception {
    }

    @Test
    public void editAccount() throws Exception {
    }

}