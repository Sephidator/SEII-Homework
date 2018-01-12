package test.java.unit_test.purchasedataservice_unit_test;

import main.java.dataservice.purchasedataservice.PurchaseRefundBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.purchasebill.PurchaseRefundBillPO;
import org.junit.Test;

import java.rmi.Naming;
import java.util.ArrayList;

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
        ArrayList<PurchaseRefundBillPO> list = service.finds(new BillQueryPO("审批通过", null, null, "进货退货单", null, null));

        PurchaseRefundBillPO purchaseRefundBillPO = list.get(0);
        purchaseRefundBillPO.setState("待审批");

        assertEquals("JHTHD", service.insert(purchaseRefundBillPO).substring(0, 5));
    }
}