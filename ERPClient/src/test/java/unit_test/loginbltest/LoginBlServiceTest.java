package test.java.unit_test.loginbltest;

import main.java.businesslogicfactory.logblfactory.LogBlFactory;
import main.java.businesslogicfactory.loginblfactory.LoginBlFactory;
import main.java.businesslogicservice.loginblservice.LoginBlService;
import main.java.client_blservicestub.loginblservicestub.LoginBlServiceStub;
import main.java.exception.LoginException;
import main.java.exception.NotExistException;
import org.junit.Test;
import sun.rmi.runtime.Log;

import static org.junit.Assert.assertEquals;

public class LoginBlServiceTest {
    LoginBlService service= LoginBlFactory.getService();

    @Test
    public void login() throws Exception {
        try{
            assertEquals("User",service.login("","").getID().substring(0,4));
        }catch (LoginException e){}
    }

}