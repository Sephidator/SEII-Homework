package stub_driver.Server.test.java;

import dataservice.accountdataservice.AccountInfoDataService;
import org.junit.Test;
import stub_driver.Server.main.java.AccountInfoDataServiceStub;

import static org.junit.Assert.*;

/**
 * Created by wangn on 2017.10.22.
 */
public class AccountInfoDataServiceDriver {

    AccountInfoDataService accountInfoDataService = new AccountInfoDataServiceStub();
    @Test
    public void getAccountName() throws Exception {
        assertEquals(accountInfoDataService.getAccountName("621226240201712345"),"wang");
    }

    @Test
    public void getAccountRem() throws Exception {
        assertEquals(accountInfoDataService.getAccountRem("621226240201712345"),0);
    }

}