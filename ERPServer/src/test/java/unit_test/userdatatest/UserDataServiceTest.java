package test.java.unit_test.userdatatest;

import main.java.dataservice.userdataservice.UserDataService;
import main.java.po.user.UserPO;
import org.junit.Test;

import java.rmi.Naming;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class UserDataServiceTest {
    UserDataService service;

    public UserDataServiceTest() throws Exception {
        service = (UserDataService) Naming.lookup("rmi://127.0.0.1:7200/UserDataService");
    }

    @Test
    public void finds() throws Exception {
        assertEquals(true, service.finds(null).size() >= 0);
    }

    @Test
    public void insert() throws Exception {
        ArrayList<UserPO> list = service.finds(null);

        UserPO userPO = list.get(list.size() - 1);
        userPO.setJobName(userPO.getJobName() + "0");

        assertEquals("User", service.insert(userPO).substring(0, 4));
    }

    @Test
    public void login() throws Exception {
        ArrayList<UserPO> list = service.finds(null);

        UserPO userPO = list.get(list.size() - 1);

        assertEquals(userPO.getName(), service.login(userPO.getJobName(), userPO.getPassword()).getName());
    }

}