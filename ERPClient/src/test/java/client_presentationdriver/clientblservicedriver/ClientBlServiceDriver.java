package test.java.client_presentationdriver.clientblservicedriver;

import main.java.businesslogicservice.clientblservice.ClientBlService;
import main.java.client_blservicestub.clientblservicestub.ClientBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientBlServiceDriver {
    ClientBlService service=new ClientBlServiceStub();

    @Test
    public void getClientList() throws Exception {
        assertEquals(0,service.getClientList(null).size());
    }

    @Test
    public void addClient() throws Exception {
        assertEquals("",service.addClient(null));
    }

    @Test
    public void editClient() throws Exception {
    }

    @Test
    public void deleteClient() throws Exception {
    }

    @Test
    public void getUserList() throws Exception {
        assertEquals(0,service.getUserList(null).size());
    }

}