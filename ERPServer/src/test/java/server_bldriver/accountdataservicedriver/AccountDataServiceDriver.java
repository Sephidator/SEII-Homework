package test.java.server_bldriver.accountdataservicedriver;

import main.java.data.datautility.ResultMessage;
import main.java.dataservice.accountdataservice.AccountDataService;
import org.junit.Test;
import main.java.po.account.AccountPO;
import main.java.po.account.AccountQueryPO;
import main.java.server_dataservicestub.accountdataservicestub.AccountDataServiceStub;

import static org.junit.Assert.*;

public class AccountDataServiceDriver {
    AccountDataService accountDataService = new AccountDataServiceStub();
    @Test
    public void find() throws Exception {
        assertEquals("Account-20171106-00001",accountDataService.find(new AccountQueryPO()).getID());
    }

    @Test
    public void getID() throws Exception {
        assertEquals("Account-20171106-00001",accountDataService.getID());
    }

    @Test
    public void insert() throws Exception {
        assertEquals(ResultMessage.success,accountDataService.insert(new AccountPO()));
    }

    @Test
    public void delete() throws Exception {
        assertEquals(ResultMessage.success,accountDataService.delete(new AccountPO()));
    }

    @Test
    public void update() throws Exception {
        assertEquals(ResultMessage.success,accountDataService.update(new AccountPO()));
    }

}