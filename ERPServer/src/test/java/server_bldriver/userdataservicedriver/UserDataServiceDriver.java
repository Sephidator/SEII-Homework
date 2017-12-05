package test.java.server_bldriver.userdataservicedriver;

import main.java.dataservice.userdataservice.UserDataService;
import main.java.po.user.UserPO;
import main.java.server_dataservicestub.userdataservicestub.UserDataServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDataServiceDriver {
    UserDataService service = new UserDataServiceStub();

    @Test
    public void find() throws Exception {
        assertEquals(true, service.find("").isVisible());
    }

    @Test
    public void finds() throws Exception {
        assertEquals(1, service.finds(null).size());
    }

    @Test
    public void insert() throws Exception {
        assertEquals("00000001", service.insert(new UserPO()));
    }

    @Test
    public void login() throws Exception {
        assertEquals("aa", service.login("admin", "admin").getName());
    }

}