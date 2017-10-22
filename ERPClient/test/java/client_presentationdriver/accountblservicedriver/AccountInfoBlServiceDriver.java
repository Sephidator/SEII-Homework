package stub_driver.Client.test.java;

import businesslogicservice.accountblservice.AccountInfoBlService;
import org.junit.Test;
import stub_driver.Client.main.java.AccountInfoBlServiceStub;

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