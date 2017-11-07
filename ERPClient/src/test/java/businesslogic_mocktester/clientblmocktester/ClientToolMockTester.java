package businesslogic_mocktester.clientblmocktester;

import businesslogic.clientbl.ClientTool;
import businesslogic_mock.clientblmock.ClientToolMock;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientToolMockTester {
    ClientTool tool=new ClientToolMock();

    @Test
    public void getClientList() throws Exception {
        assertEquals(1,tool.getClientList(null).size());
    }

}