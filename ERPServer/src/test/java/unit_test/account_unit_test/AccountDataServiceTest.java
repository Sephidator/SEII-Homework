package test.java.unit_test.account_unit_test;

import main.java.dataservice.accountdataservice.AccountDataService;
import main.java.po.account.AccountPO;
import org.junit.Test;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;

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
    public void insert() throws RemoteException {
        ArrayList<AccountPO> list = service.finds(null);
        AccountPO accountPO = list.get(list.size() - 1);

        accountPO.setBankAccount(accountPO.getBankAccount() + "0");
        accountPO.setName(accountPO.getName() + "0");
        assertEquals("Account", service.insert(accountPO).substring(0, 7));
    }
}