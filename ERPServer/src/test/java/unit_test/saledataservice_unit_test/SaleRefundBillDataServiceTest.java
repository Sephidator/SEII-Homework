package test.java.unit_test.saledataservice_unit_test;

import main.java.dataservice.saledataservice.SaleRefundBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.salebill.SaleRefundBillPO;
import org.junit.Test;

import java.rmi.Naming;
import java.util.ArrayList;

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
        ArrayList<SaleRefundBillPO> list = service.finds(new BillQueryPO("审批通过", null, null, "销售退货单", null, null));

        SaleRefundBillPO saleRefundBillPO = list.get(0);
        saleRefundBillPO.setState("待审批");

        assertEquals("XSTHD", service.insert(saleRefundBillPO).substring(0, 5));
    }

}