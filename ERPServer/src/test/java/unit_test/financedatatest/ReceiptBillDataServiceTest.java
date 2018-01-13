package test.java.unit_test.financedatatest;

import main.java.dataservice.clientdataservice.ClientDataService;
import main.java.dataservice.financedataservice.ReceiptBillDataService;
import main.java.dataservice.userdataservice.UserDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.financebill.ReceiptBillPO;
import main.java.po.client.ClientPO;
import main.java.po.user.UserPO;
import main.java.po.user.UserQueryPO;
import org.junit.Test;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ReceiptBillDataServiceTest {
    ReceiptBillDataService service;

    public ReceiptBillDataServiceTest() throws Exception {
        service = (ReceiptBillDataService) Naming.lookup("rmi://127.0.0.1:7200/ReceiptBillDataService");
    }

    @Test
    public void finds() throws Exception {
        assertEquals(true, service.finds(new BillQueryPO("审批通过", null, null, "收款单", null, null)).size() >= 0);
    }

    @Test
    public void insert() throws Exception {
        UserPO userPO = ((UserDataService) Naming.lookup("rmi://127.0.0.1:7200/UserDataService")).finds(new UserQueryPO("", "财务人员")).get(0);
        ClientPO clientPO = ((ClientDataService) Naming.lookup("rmi://127.0.0.1:7200/ClientDataService")).finds(null).get(0);

        ReceiptBillPO receiptBillPO = new ReceiptBillPO("草稿", new Date(), userPO.getID(), "", 0, clientPO.getID(), new ArrayList<>());

        assertEquals("SKD", service.insert(receiptBillPO).substring(0, 3));
    }

}