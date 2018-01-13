package test.java.unit_test.financedatatest;

import main.java.dataservice.clientdataservice.ClientDataService;
import main.java.dataservice.financedataservice.PaymentBillDataService;
import main.java.dataservice.userdataservice.UserDataService;
import main.java.po.client.ClientPO;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.financebill.PaymentBillPO;
import main.java.po.user.UserPO;
import main.java.po.user.UserQueryPO;
import org.junit.Test;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class PaymentBillDataServiceTest {
    PaymentBillDataService service;

    public PaymentBillDataServiceTest() throws Exception {
        service = (PaymentBillDataService) Naming.lookup("rmi://127.0.0.1:7200/PaymentBillDataService");
    }

    @Test
    public void finds() throws Exception {
        assertEquals(true, service.finds(new BillQueryPO("审批通过", null, null, "付款单", null, null)).size() >= 0);
    }

    @Test
    public void insert() throws Exception {
        UserPO userPO = ((UserDataService) Naming.lookup("rmi://127.0.0.1:7200/UserDataService")).finds(new UserQueryPO("", "财务人员")).get(0);
        ClientPO clientPO = ((ClientDataService) Naming.lookup("rmi://127.0.0.1:7200/ClientDataService")).finds(null).get(0);

        PaymentBillPO paymentBillPO = new PaymentBillPO("草稿", new Date(), userPO.getID(), "", 0, clientPO.getID(), new ArrayList<>());

        assertEquals("FKD", service.insert(paymentBillPO).substring(0, 3));
    }

}