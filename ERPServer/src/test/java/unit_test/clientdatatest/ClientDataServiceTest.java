package test.java.unit_test.clientdatatest;

import main.java.dataservice.clientdataservice.ClientDataService;
import main.java.dataservice.userdataservice.UserDataService;
import main.java.po.client.ClientPO;
import main.java.po.user.UserPO;
import main.java.po.user.UserQueryPO;
import org.junit.Test;

import java.rmi.Naming;

import static org.junit.Assert.assertEquals;

public class ClientDataServiceTest {
    ClientDataService service;

    public ClientDataServiceTest() throws Exception {
        service = (ClientDataService) Naming.lookup("rmi://127.0.0.1:7200/ClientDataService");
    }

    @Test
    public void find() throws Exception {
        assertEquals("Client00000001", service.find("Client00000001").getID());
    }

    @Test
    public void finds() throws Exception {
        assertEquals(true, service.finds(null).size() >= 0);
    }

    @Test
    public void insert() throws Exception {
        UserPO userPO = ((UserDataService) Naming.lookup("rmi://127.0.0.1:7200/UserDataService")).finds(new UserQueryPO("", "进货销售人员")).get(0);
        assertEquals("Client", service.insert(new ClientPO("供应商", 5, "Knuth", "10987654321", "仙林", "123456", "123456789@qq.com", 0, 0, 10000, userPO.getID())).substring(0, 6));
    }

}