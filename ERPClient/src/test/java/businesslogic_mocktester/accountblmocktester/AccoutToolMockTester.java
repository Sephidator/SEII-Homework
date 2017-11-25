package test.java.businesslogic_mocktester.accountblmocktester;

import main.java.businesslogic.accountbl.AccountTool;
import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogic_mock.accountblmock.AccountToolMock;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccoutToolMockTester {
    AccountTool accountTool = new AccountToolMock();
    @Test
    public void getAccountList() throws Exception {
        assertEquals(1,accountTool.getAccountList(null).size());
    }

    @Test
    public void editAccount() throws Exception {
        assertEquals(ResultMessage.success,accountTool.editAccount(null));
    }

}