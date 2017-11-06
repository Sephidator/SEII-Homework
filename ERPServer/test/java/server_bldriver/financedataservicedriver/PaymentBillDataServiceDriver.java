package server_bldriver.financedataservicedriver;

import data.datautility.ResultMessage;
import dataservice.financedataservice.PaymentBillDataService;
import org.junit.Test;
import po.bill.BillQueryPO;
import po.bill.financebill.PaymentBillPO;
import server_dataservicestub.financedataservicestub.PaymentBillDataServiceStub;

import static org.junit.Assert.*;

public class PaymentBillDataServiceDriver {
    PaymentBillDataService paymentBillDataService = new PaymentBillDataServiceStub();
    @Test
    public void find() throws Exception {
        assertEquals("FKD-20171106-00001",paymentBillDataService.find(new BillQueryPO()).get(0).getID());
    }

    @Test
    public void getID() throws Exception {
        assertEquals("FKD-20171106-00001",paymentBillDataService.getID());
    }

    @Test
    public void insert() throws Exception {
        assertEquals(ResultMessage.success,paymentBillDataService.insert(new PaymentBillPO()));
    }

    @Test
    public void update() throws Exception {
        assertEquals(ResultMessage.success,paymentBillDataService.update(new PaymentBillPO()));
    }

}