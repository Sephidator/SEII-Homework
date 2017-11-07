package client_presentationdriver.accountblservicedriver;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.accountblservice.AccountBlService;
import client_blservicestub.accountblservicestub.AccountBlServiceStub;
import vo.account.AccountVO;

import static org.junit.Assert.*;

public class AccountBlServiceDriver {
    AccountBlService accountBlService= new AccountBlServiceStub();
    @org.junit.Test
    public void getID() throws Exception {
        assertEquals("Account-20171106-00001",accountBlService.getID());
    }

    @org.junit.Test
    public void addAccount() throws Exception {
        AccountVO accountVO = new AccountVO();
        assertEquals(ResultMessage.success,accountBlService.addAccount(accountVO));
    }

    @org.junit.Test
    public void deleteAccount() throws Exception {
        AccountVO accountVO = new AccountVO();
        assertEquals(ResultMessage.success,accountBlService.deleteAccount(accountVO));
    }

}