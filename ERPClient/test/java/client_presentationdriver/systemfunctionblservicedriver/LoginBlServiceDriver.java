package client_presentationdriver.systemfunctionblservicedriver;

import businesslogicservice.systemfunctionicservice.LoginBlService;
import client_blservicestub.systemfunctionblservicestub.LoginBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginBlServiceDriver {
    LoginBlService service=new LoginBlServiceStub();

    @Test
    public void findClient() throws Exception {
        assertEquals(service.findClient("123","123").getID(),"123");
    }

}