package test.java.server_bldriver.accountdataservicedriver;

import main.java.dataservice.accountdataservice.AccountDataService;
import main.java.server_dataservicestub.accountdataservicestub.AccountDataServiceStub;
import org.junit.Test;
import main.java.po.account.AccountPO;

import static org.junit.Assert.assertEquals;

public class AccountDataServiceDriver {
    AccountDataService accountDataService = new AccountDataServiceStub();

    @Test
    public void find() throws Exception {
        assertEquals(1, accountDataService.find(null).size());
    }

    @Test
    public void insert() throws Exception {
        assertEquals("00000001", accountDataService.insert(new AccountPO()));
    }

}