package server_bldriver.userdataservicedriver;

import data.datautility.ResultMessage;
import dataservice.userdataservice.UserDataService;
import org.junit.Test;
import po.UserPO;
import server_dataservicestub.userdataservicestub.UserDataServiceStub;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class UserDataServiceDriver {
    UserDataService userDataService = new UserDataServiceStub();
    ArrayList<UserPO> pos = new ArrayList<>();
    UserPO po = new UserPO(1, 33, false, "马冬梅", "mdm123456", "queen", null);


    @Test
    public void finds() throws Exception {
        pos.clear();
        pos.add(po);
        assertEquals(userDataService.finds().size(), pos.size());
    }

    @Test
    public void find() throws Exception {
        assertEquals(userDataService.find("mdm123456").getName(), "马冬梅");
    }

    @Test
    public void insert() throws Exception {
        assertEquals(userDataService.insert(null), ResultMessage.success);
    }

    @Test
    public void update() throws Exception {
        assertEquals(userDataService.update(null), ResultMessage.success);
    }

    @Test
    public void delete() throws Exception {
        assertEquals(userDataService.insert(null), ResultMessage.success);
    }

}