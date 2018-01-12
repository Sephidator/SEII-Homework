package test.java.unit_test.financedataservice_unit_test;

import main.java.dataservice.financedataservice.ReceiptBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.financebill.ReceiptBillPO;
import org.junit.Test;

import java.rmi.Naming;
import java.util.ArrayList;

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
        ArrayList<ReceiptBillPO> list = service.finds(new BillQueryPO("审批通过", null, null, "收款单", null, null));

        ReceiptBillPO receiptBillPO = list.get(0);
        receiptBillPO.setState("待审批");

        assertEquals("SKD", service.insert(receiptBillPO).substring(0, 3));
    }

}