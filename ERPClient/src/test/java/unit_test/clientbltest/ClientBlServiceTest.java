package test.java.unit_test.clientbltest;

import main.java.businesslogicfactory.clientblfactory.ClientBlFactory;
import main.java.businesslogicservice.clientblservice.ClientBlService;
import main.java.client_blservicestub.clientblservicestub.ClientBlServiceStub;
import main.java.vo.client.ClientVO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClientBlServiceTest {
    ClientBlService service= ClientBlFactory.getService();

    @Test
    public void getClientList() throws Exception {
        assertEquals(true,service.getClientList(null).size()>=0);
    }

    @Test
    public void addClient() throws Exception {
        assertEquals("Client",service.addClient(new ClientVO()).substring(0,6));
    }

    @Test
    public void editClient() throws Exception {
    }
    
    @Test
    public void getUserList() throws Exception {
        assertEquals(true,service.getUserList(null).size()>=0);
    }

}