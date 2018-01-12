package test.java.unit_test.purchasedataservice_unit_test;

import main.java.dataservice.purchasedataservice.PurchaseTradeBillDataService;
import main.java.dataservice.purchasedataservice.PurchaseTradeBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.purchasebill.PurchaseTradeBillPO;
import main.java.po.bill.purchasebill.PurchaseTradeBillPO;
import main.java.server_dataservicestub.purchasedataservicestub.PurchaseTradeBillDataServiceStub;
import org.junit.Test;

import java.rmi.Naming;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PurchaseTradeBillDataServiceTest {
    PurchaseTradeBillDataService service;

    public PurchaseTradeBillDataServiceTest() throws Exception {
        service = (PurchaseTradeBillDataService) Naming.lookup("rmi://127.0.0.1:7200/PurchaseTradeBillDataService");
    }

    @Test
    public void finds() throws Exception {
        assertEquals(true, service.finds(new BillQueryPO("审批通过", null, null, "进货单", null, null)).size() >= 0);
    }

    @Test
    public void insert() throws Exception {
        ArrayList<PurchaseTradeBillPO> list = service.finds(new BillQueryPO("审批通过", null, null, "进货单", null, null));

        PurchaseTradeBillPO purchaseTradeBillPO = list.get(0);
        purchaseTradeBillPO.setState("待审批");

        assertEquals("JHD", service.insert(purchaseTradeBillPO).substring(0, 3));
    }

}