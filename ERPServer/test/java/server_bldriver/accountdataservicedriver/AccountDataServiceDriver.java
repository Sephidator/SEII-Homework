package server_bldriver.accountdataservicedriver;

import data.datautility.ResultMessage;
import dataservice.accountdataservice.AccountDataService;
import org.junit.Test;
import po.AccountPO;
import po.UserPO;
import server_dataservicestub.accountdataservicestub.AccountDataServiceStub;

import static org.junit.Assert.*;

/**
 * Created by wangn on 2017.10.22.
 */
public class AccountDataServiceDriver {

    AccountDataService accountDataService = new AccountDataServiceStub();
    @Test
    public void rightControl() throws Exception {
        assertEquals(accountDataService.rightControl(new UserPO(0, 45, true,"wang","finance","1234",new  String[]{})),"true");
    }

    @Test
    public void find() throws Exception {
        assertEquals(accountDataService.find("6212262402017123456").getAccountName(),"wang");
    }

    @Test
    public void insert() throws Exception {
        assertEquals(accountDataService.insert(new AccountPO("621226240201712345","wang",0,true)), ResultMessage.success);
    }

    @Test
    public void delete() throws Exception {
        assertEquals(accountDataService.delete(new AccountPO("621226240201712345","wang",0,true)),ResultMessage.success);
    }

    @Test
    public void update() throws Exception {
        assertEquals(accountDataService.update(new AccountPO("621226240201712345","wang",0,true)),ResultMessage.success);
    }

    @Test
    public void init() throws Exception {
        assertEquals(accountDataService.init(), ResultMessage.success);
    }

    @Test
    public void finish() throws Exception {
        assertEquals(accountDataService.finish(),ResultMessage.success);
    }

}