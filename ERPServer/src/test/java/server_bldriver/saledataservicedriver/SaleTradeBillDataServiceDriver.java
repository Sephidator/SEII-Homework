package test.java.server_bldriver.saledataservicedriver;

import main.java.dataservice.saledataservice.SaleTradeBillDataService;
import main.java.po.bill.salebill.SaleTradeBillPO;
import main.java.server_dataservicestub.saledataservicestub.SaleTradeBillDataServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class SaleTradeBillDataServiceDriver {
    SaleTradeBillDataService service = new SaleTradeBillDataServiceStub();

    @Test
    public void findsByReport() throws Exception {
        assertEquals(1, service.findsByReport(null).size());
    }

    @Test
    public void findsByBill() throws Exception {
        assertEquals(1, service.findsByBill(null).size());
    }

    @Test
    public void insert() throws Exception {
        assertEquals("XSD-20171212-12345", service.insert(new SaleTradeBillPO()));
    }

}