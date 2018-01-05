package test.java.businesslogic_mocktester.accountblmocktester;

import main.java.businesslogic.accountbl.AccountBl;
import main.java.businesslogic.accountbl.AccountTool;
import main.java.businesslogic_mock.accountblmock.AccountToolMock;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountToolMockTester {
    AccountTool tool=new AccountToolMock();

    @Test
    public void getAccountList() throws Exception {
        assertEquals(0,tool.getAccountList(null).size());
    }

    @Test
    public void editAccount() throws Exception {
    }

    @Test
    public void find() throws Exception {
        assertEquals("",tool.find(null).getID());
    }

}