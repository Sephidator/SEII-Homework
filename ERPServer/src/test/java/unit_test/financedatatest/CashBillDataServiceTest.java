package test.java.unit_test.financedatatest;

import main.java.dataservice.financedataservice.CashBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.financebill.CashBillPO;
import org.junit.Test;

import java.rmi.Naming;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CashBillDataServiceTest {
    CashBillDataService service;

    public CashBillDataServiceTest() throws Exception {
        service = (CashBillDataService) Naming.lookup("rmi://127.0.0.1:7200/CashBillDataService");
    }

    @Test
    public void finds() throws Exception {
        assertEquals(true, service.finds(new BillQueryPO("审批通过", null, null, "现金费用单", null, null)).size() >= 0);
    }

    @Test
    public void insert() throws Exception {
        ArrayList<CashBillPO> list = service.finds(new BillQueryPO("审批通过", null, null, "现金费用单", null, null));

        CashBillPO cashBillPO = list.get(0);
        cashBillPO.setState("待审批");

        assertEquals("XJFYD", service.insert(cashBillPO).substring(0, 5));
    }

}