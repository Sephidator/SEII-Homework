package test.java.unit_test.reportbltest;

import main.java.businesslogicservice.reportblservice.SaleDetailBlService;
import main.java.client_blservicestub.reportblservicestub.SaleDetailBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SaleDetailBlServiceTest {
    SaleDetailBlService service=new SaleDetailBlServiceStub();

    @Test
    public void getSaleRecordList() throws Exception {
        assertEquals(0,service.getSaleRecordList(null).size());
    }

}