package test.java.unit_test.reportbltest;

import main.java.businesslogicfactory.reportblfactory.SaleDetailBlFactory;
import main.java.businesslogicservice.reportblservice.SaleDetailBlService;
import main.java.client_blservicestub.reportblservicestub.SaleDetailBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SaleDetailBlServiceTest {
    SaleDetailBlService service= SaleDetailBlFactory.getService();

    @Test
    public void getSaleRecordList() throws Exception {
        assertEquals(true,service.getSaleRecordList(null).size()>=0);
    }

}