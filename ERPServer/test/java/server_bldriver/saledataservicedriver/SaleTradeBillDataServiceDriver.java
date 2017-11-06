package server_bldriver.saledataservicedriver;

import data.datautility.ResultMessage;
import dataservice.saledataservice.SaleTradeBillDataService;
import org.junit.Test;
import server_dataservicestub.saledataservicestub.SaleTradeBillDataServiceStub;

import static org.junit.Assert.assertEquals;

public class SaleTradeBillDataServiceDriver {
    SaleTradeBillDataService service=new SaleTradeBillDataServiceStub();
    
    @Test
    public void find() throws Exception {
        assertEquals(1,service.find(null).size());
    }

    @Test
    public void getID() throws Exception {
        assertEquals("123",service.getID());
    }

    @Test
    public void insert() throws Exception {
        assertEquals(ResultMessage.success,service.insert(null));
    }

    @Test
    public void update() throws Exception {
        assertEquals(ResultMessage.success,service.update(null));
    }

}