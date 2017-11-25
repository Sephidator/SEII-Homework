package test.java.server_bldriver.financedataservicedriver;

import main.java.data.datautility.ResultMessage;
import main.java.dataservice.financedataservice.CashBillDataService;
import org.junit.Test;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.financebill.CashBillPO;
import main.java.server_dataservicestub.financedataservicestub.CashBillDataServiceStub;

import static org.junit.Assert.*;

public class CashBillDataServiceDriver {
    CashBillDataService cashBillDataService = new CashBillDataServiceStub();
    @Test
    public void find() throws Exception {
        assertEquals("XJFYD-20171106-00001",cashBillDataService.find(new BillQueryPO()).get(0).getID());
    }

    @Test
    public void getID() throws Exception {
        assertEquals("XJFYD-20171106-00001",cashBillDataService.getID());
    }

    @Test
    public void insert() throws Exception {
        assertEquals(ResultMessage.success,cashBillDataService.insert(new CashBillPO()));
    }

    @Test
    public void update() throws Exception {
        assertEquals(ResultMessage.success,cashBillDataService.update(new CashBillPO()));
    }

}