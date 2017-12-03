package test.java.server_bldriver.saledataservicedriver;

import main.java.dataservice.saledataservice.SaleTradeBillDataService;
import main.java.po.bill.salebill.SaleTradeBillPO;
import main.java.server_dataservicestub.saledataservicestub.SaleTradeBillDataServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class SaleTradeBillDataServiceDriver {
    SaleTradeBillDataService service = new SaleTradeBillDataServiceStub();

    @Test
    public void findByReport() throws Exception {
        assertEquals(1, service.findByReport(null).size());
    }

    @Test
    public void findByBill() throws Exception {
        assertEquals(1, service.findByBill(null).size());
    }

    @Test
    public void insert() throws Exception {
        assertEquals("XSD-20171212-12345", service.insert(new SaleTradeBillPO()));
    }

}