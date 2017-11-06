package server_bldriver.userdataservicedriver;

import data.datautility.ResultMessage;
import dataservice.userdataservice.UserDataService;
import org.junit.Test;
import po.user.UserPO;
import po.user.UserQueryPO;
import server_dataservicestub.userdataservicestub.UserDataServiceStub;

import static org.junit.Assert.*;

public class UserDataServiceDriver {
    UserDataService service = new UserDataServiceStub();

    @Test
    public void find() throws Exception {
        assertEquals(service.find(new UserQueryPO()).size(), 4);
    }

    @Test
    public void insert() throws Exception {
        assertEquals(service.insert(new UserPO()), ResultMessage.success);
    }

    @Test
    public void delete() throws Exception {
        assertEquals(service.delete(new UserPO()), ResultMessage.success);
    }

    @Test
    public void update() throws Exception {
        assertEquals(service.update(new UserPO()), ResultMessage.success);
    }

    @Test
    public void getUserID() throws Exception {
        assertEquals(service.getUserID(), "678910");
    }

}