package test.java.unit_test.saledatatest;

import main.java.dataservice.clientdataservice.ClientDataService;
import main.java.dataservice.saledataservice.SaleTradeBillDataService;
import main.java.dataservice.userdataservice.UserDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.salebill.SaleTradeBillPO;
import main.java.po.client.ClientPO;
import main.java.po.user.UserPO;
import main.java.po.user.UserQueryPO;
import org.junit.Test;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class SaleTradeBillDataServiceTest {
    SaleTradeBillDataService service;

    public SaleTradeBillDataServiceTest() throws Exception {
        service = (SaleTradeBillDataService) Naming.lookup("rmi://127.0.0.1:7200/SaleTradeBillDataService");
    }

    @Test
    public void findsByReport() throws Exception {
        assertEquals(true, service.findsByReport(null).size() >= 0);
    }

    @Test
    public void findsByBill() throws Exception {
        assertEquals(true, service.findsByBill(new BillQueryPO("审批通过", null, null, "销售单", null, null)).size() >= 0);
    }

    @Test
    public void insert() throws Exception {
        UserPO userPO = ((UserDataService) Naming.lookup("rmi://127.0.0.1:7200/UserDataService")).finds(new UserQueryPO("", "进货销售人员")).get(0);
        ArrayList<ClientPO> list = ((ClientDataService) Naming.lookup("rmi://127.0.0.1:7200/ClientDataService")).finds(null);

        ClientPO clientPO = new ClientPO();
        for (ClientPO temp : list) {
            if (temp.getCategory().equals("销售商")) {
                clientPO = temp;
                break;
            }
        }
        SaleTradeBillPO saleTradeBillPO = new SaleTradeBillPO("草稿", new Date(), userPO.getID(), "", clientPO.getID(), clientPO.getSalesmanID(), new ArrayList<>(), "", 0, 0, 0, 0);

        assertEquals("XSD", service.insert(saleTradeBillPO).substring(0, 3));
    }


}