package server_bldriver.purchasedataservicedriver;

import data.datautility.ResultMessage;
import dataservice.purchasedataservice.PurchaseTradeBillDataService;
import org.junit.Test;
import server_dataservicestub.purchasedataservicestub.PurchaseTradeBillDataServiceStub;

import static org.junit.Assert.*;

public class PurchaseTradeBillDataServiceDriver {
    PurchaseTradeBillDataService service=new PurchaseTradeBillDataServiceStub();

    @Test
    public void insert() throws Exception {
        assertEquals( ResultMessage.success,service.insert(null));
    }

    @Test
    public void find() throws Exception {
        assertEquals("123",service.find("123").getID());
    }

    @Test
    public void delete() throws Exception {
        assertEquals( ResultMessage.success,service.delete(null));
    }

    @Test
    public void update() throws Exception {
        assertEquals( ResultMessage.success,service.update(null));
    }

    @Test
    public void getID() throws Exception {
        assertEquals( "123",service.getID());
    }

    @Test
    public void getList() throws Exception {
        assertEquals( 2,service.getList().size());
    }

}