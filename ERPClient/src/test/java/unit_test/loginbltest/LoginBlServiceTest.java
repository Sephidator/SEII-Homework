package test.java.unit_test.loginbltest;

import main.java.businesslogicservice.loginblservice.LoginBlService;
import main.java.client_blservicestub.loginblservicestub.LoginBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginBlServiceTest {
    LoginBlService service=new LoginBlServiceStub();

    @Test
    public void login() throws Exception {
        assertEquals("",service.login(null,null).getID());
    }

    @Test
    public void logout() throws Exception {
    }

}