package server_bldriver.accountdataservicedriver;

import dataservice.accountdataservice.AccountInfoDataService;
import org.junit.Test;
import server_dataservicestub.accountdataservicestub.AccountInfoDataServiceStub;

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
        assertEquals(accountInfoDataService.getAccountRem("621226240201712345"),0,0.1);
    }

}