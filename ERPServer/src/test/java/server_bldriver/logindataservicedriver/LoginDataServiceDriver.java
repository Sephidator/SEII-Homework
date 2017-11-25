package test.java.server_bldriver.logindataservicedriver;

import main.java.dataservice.logindataservice.LoginDataService;
import org.junit.Test;
import main.java.server_dataservicestub.logindataservicestub.LoginDataServiceStub;

import static org.junit.Assert.*;

public class LoginDataServiceDriver {
    LoginDataService service=new LoginDataServiceStub();

    @Test
    public void login() throws Exception {
        assertEquals("123",service.login("123","123").getID());
    }

}