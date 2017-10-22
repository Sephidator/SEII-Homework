package client_presentationdriver.accountblservicedriver;

import businesslogicservice.accountblservice.AccountInfoBlService;
import client_blservicestub.accountblservicestub.AccountInfoBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wangn on 2017.10.22.
 */
public class AccountInfoBlServiceDriver {

    AccountInfoBlService accountInfoBlService = new AccountInfoBlServiceStub();
    @Test
    public void mockAccountName() throws Exception {
        assertEquals(accountInfoBlService.mockAccountName("6212262402017123456","wang"), AccountInfoBlService.ResultMessage.Success);
    }

    @Test
    public void delAccount() throws Exception {
        assertEquals(accountInfoBlService.delAccount("6212262402017123456"), AccountInfoBlService.ResultMessage.Success);
    }

    @Test
    public void updateAccount() throws Exception {
        assertEquals(accountInfoBlService.updateAccount(), AccountInfoBlService.ResultMessage.Success);
    }

}