package test.java.businesslogic_mocktester.clientblmocktester;

import main.java.businesslogic.clientbl.ClientBl;
import main.java.businesslogic.clientbl.ClientTool;
import main.java.businesslogic_mock.clientblmock.ClientToolMock;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientToolMockTester {
    ClientTool tool=new ClientToolMock();

    @Test
    public void getClientList() throws Exception {
        assertEquals(0,tool.getClientList(null).size());
    }

    @Test
    public void editClient() throws Exception {
    }

    @Test
    public void find() throws Exception {
        assertEquals("",tool.find(null).getID());
    }

}