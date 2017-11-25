package test.java.client_presentationdriver.logblservicedriver;

import main.java.businesslogicservice.loginblservice.LoginBlService;
import main.java.client_blservicestub.logblservicestub.LoginBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginBlServiceDriver {
    LoginBlService service=new LoginBlServiceStub();

    @Test
    public void login() throws Exception {
        assertEquals("123",service.login("123","123").getID());
    }

}