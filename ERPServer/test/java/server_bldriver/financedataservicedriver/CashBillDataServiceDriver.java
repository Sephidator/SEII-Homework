package server_bldriver.financedataservicedriver;

import data.datautility.ResultMessage;
import dataservice.financedataservice.CashBillDataService;
import org.junit.Test;
import po.bill.BillQueryPO;
import po.bill.financebill.CashBillPO;
import server_dataservicestub.financedataservicestub.CashBillDataServiceStub;

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