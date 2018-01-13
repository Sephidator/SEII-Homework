package test.java.unit_test.saledatatest;

import main.java.dataservice.saledataservice.SaleTradeBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.salebill.SaleTradeBillPO;
import org.junit.Test;

import java.rmi.Naming;
import java.util.ArrayList;

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
        ArrayList<SaleTradeBillPO> list = service.findsByBill(new BillQueryPO("审批通过", null, null, "销售单", null, null));

        SaleTradeBillPO saleTradeBillPO = list.get(0);
        saleTradeBillPO.setState("待审批");

        assertEquals("XSD", service.insert(saleTradeBillPO).substring(0, 3));
    }


}