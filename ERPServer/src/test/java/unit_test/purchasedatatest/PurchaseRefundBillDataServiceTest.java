package test.java.unit_test.purchasedatatest;

import main.java.dataservice.clientdataservice.ClientDataService;
import main.java.dataservice.purchasedataservice.PurchaseRefundBillDataService;
import main.java.dataservice.userdataservice.UserDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.purchasebill.PurchaseRefundBillPO;
import main.java.po.client.ClientPO;
import main.java.po.user.UserPO;
import main.java.po.user.UserQueryPO;
import org.junit.Test;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class PurchaseRefundBillDataServiceTest {
    PurchaseRefundBillDataService service;

    public PurchaseRefundBillDataServiceTest() throws Exception {
        service = (PurchaseRefundBillDataService) Naming.lookup("rmi://127.0.0.1:7200/PurchaseRefundBillDataService");
    }

    @Test
    public void finds() throws Exception {
        assertEquals(true, service.finds(new BillQueryPO("审批通过", null, null, "进货退货单", null, null)).size() >= 0);
    }

    @Test
    public void insert() throws Exception {
        UserPO userPO = ((UserDataService) Naming.lookup("rmi://127.0.0.1:7200/UserDataService")).finds(new UserQueryPO("", "进货销售人员")).get(0);

        ArrayList<ClientPO> list = ((ClientDataService) Naming.lookup("rmi://127.0.0.1:7200/ClientDataService")).finds(null);

        ClientPO clientPO = new ClientPO();
        for (ClientPO temp : list) {
            if (temp.getCategory().equals("供应商")) {
                clientPO = temp;
                break;
            }
        }
        PurchaseRefundBillPO purchaseRefundBillPO = new PurchaseRefundBillPO("草稿", new Date(), userPO.getID(), "", clientPO.getID(), new ArrayList<>(), 0);

        assertEquals("JHTHD", service.insert(purchaseRefundBillPO).substring(0, 5));
    }
}