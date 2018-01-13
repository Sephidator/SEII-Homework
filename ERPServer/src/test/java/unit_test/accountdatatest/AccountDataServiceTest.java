package test.java.unit_test.accountdatatest;

import main.java.dataservice.accountdataservice.AccountDataService;
import main.java.exception.ExistException;
import main.java.po.account.AccountPO;
import org.junit.Test;

import java.rmi.Naming;
import java.rmi.RemoteException;

import static org.junit.Assert.assertEquals;

public class AccountDataServiceTest {
    AccountDataService service;

    public AccountDataServiceTest() throws Exception {
        service = (AccountDataService) Naming.lookup("rmi://127.0.0.1:7200/AccountDataService");
    }


    @Test
    public void find() throws RemoteException {
        assertEquals("Account00000001", service.find("Account00000001").getID());
    }

    @Test
    public void finds() throws RemoteException {
        assertEquals(true, service.finds(null).size() >= 0);
    }

    @Test
    public void insert() throws Exception {
        try {
            assertEquals("Account", service.insert(new AccountPO("1234324235689", "户234523", 20000)).substring(0, 7));
        } catch (ExistException e) {

        }
    }
}