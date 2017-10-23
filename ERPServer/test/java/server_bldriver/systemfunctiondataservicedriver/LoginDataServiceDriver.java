package server_bldriver.systemfunctiondataservicedriver;

import dataservice.systemfunctiondataservice.LoginDataService;
import org.junit.Test;
import server_dataservicestub.systemfunctiondataservicestub.LoginDataServiceStub;

import static org.junit.Assert.*;

public class LoginDataServiceDriver {
    LoginDataService service=new LoginDataServiceStub();

    @Test
    public void findClient() throws Exception {
        assertEquals(service.findClient("123","123").getID(),null);
    }

}