package test.java.unit_test.saledatatest;

import main.java.dataservice.clientdataservice.ClientDataService;
import main.java.dataservice.saledataservice.SaleRefundBillDataService;
import main.java.dataservice.userdataservice.UserDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.salebill.SaleRefundBillPO;
import main.java.po.client.ClientPO;
import main.java.po.user.UserPO;
import main.java.po.user.UserQueryPO;
import org.junit.Test;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class SaleRefundBillDataServiceTest {
    SaleRefundBillDataService service;

    public SaleRefundBillDataServiceTest() throws Exception {
        service = (SaleRefundBillDataService) Naming.lookup("rmi://127.0.0.1:7200/SaleRefundBillDataService");
    }

    @Test
    public void finds() throws Exception {
        assertEquals(true, service.finds(new BillQueryPO("审批通过", null, null, "销售退货单", null, null)).size() >= 0);
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

        SaleRefundBillPO saleRefundBillPO = new SaleRefundBillPO("草稿", new Date(), userPO.getID(), "", clientPO.getID(), clientPO.getSalesmanID(), new ArrayList<>(), 0);
        assertEquals("XSTHD", service.insert(saleRefundBillPO).substring(0, 5));
    }

}