package test.java.server_bldriver.financedataservicedriver;

import main.java.data.datautility.ResultMessage;
import main.java.dataservice.financedataservice.PaymentBillDataService;
import org.junit.Test;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.financebill.PaymentBillPO;
import main.java.server_dataservicestub.financedataservicestub.PaymentBillDataServiceStub;

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