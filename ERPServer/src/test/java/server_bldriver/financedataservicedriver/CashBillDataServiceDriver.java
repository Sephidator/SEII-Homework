package test.java.server_bldriver.financedataservicedriver;

import main.java.dataservice.financedataservice.CashBillDataService;
import main.java.po.bill.financebill.CashBillPO;
import main.java.server_dataservicestub.financedataservicestub.CashBillDataServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class CashBillDataServiceDriver {
    CashBillDataService service = new CashBillDataServiceStub();

    @Test
    public void find() throws Exception {
        assertEquals(1, service.find(null).size());
    }

    @Test
    public void insert() throws Exception {
        assertEquals("XJFYD-20171212-12345", service.insert(new CashBillPO()));
    }

}