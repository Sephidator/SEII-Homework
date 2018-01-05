package test.java.client_presentationdriver.loginblservicedriver;

import main.java.businesslogicservice.loginblservice.LoginBlService;
import main.java.client_blservicestub.loginblservicestub.LoginBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginBlServiceDriver {
    LoginBlService service=new LoginBlServiceStub();

    @Test
    public void login() throws Exception {
        assertEquals("",service.login(null,null).getID());
    }

    @Test
    public void logout() throws Exception {
    }

}