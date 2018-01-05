package test.java.client_presentationdriver.initialblservicedriver;

import main.java.businesslogicservice.initialblservice.InitialBlService;
import main.java.client_blservicestub.initialblservicestub.InitialBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class InitialBlServiceDriver {
    InitialBlService service=new InitialBlServiceStub();

    @Test
    public void getGoodsList() throws Exception {
        assertEquals(0,service.getGoodsList(null).size());
    }

    @Test
    public void getClientList() throws Exception {
        assertEquals(0,service.getClientList(null).size());
    }

    @Test
    public void getAccountList() throws Exception {
        assertEquals(0,service.getAccountList(null).size());
    }

    @Test
    public void establishInitial() throws Exception {
        assertEquals("",service.establishInitial(null));
    }

    @Test
    public void getInitial() throws Exception {
        assertEquals(0,service.getInitial(null).size());
    }

}