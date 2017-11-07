package businesslogic_mocktester.accountblmocktester;

import businesslogic.accountbl.AccountTool;
import businesslogic.blutility.ResultMessage;
import businesslogic_mock.accountblmock.AccoutToolMock;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccoutToolMockTester {
    AccountTool accountTool = new AccoutToolMock();
    @Test
    public void getAccountList() throws Exception {
        assertEquals(1,accountTool.getAccountList(null).size());
    }

    @Test
    public void editAccount() throws Exception {
        assertEquals(ResultMessage.success,accountTool.editAccount(null));
    }

}