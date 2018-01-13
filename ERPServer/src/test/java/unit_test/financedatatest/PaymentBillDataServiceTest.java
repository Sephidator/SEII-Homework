package test.java.unit_test.financedatatest;

import main.java.dataservice.financedataservice.PaymentBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.financebill.PaymentBillPO;
import org.junit.Test;

import java.rmi.Naming;
import java.util.ArrayList;

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
        ArrayList<PaymentBillPO> list = service.finds(new BillQueryPO("审批通过", null, null, "付款单", null, null));

        PaymentBillPO paymentBillPO = list.get(0);
        paymentBillPO.setState("待审批");

        assertEquals("FKD", service.insert(paymentBillPO).substring(0, 3));
    }

}