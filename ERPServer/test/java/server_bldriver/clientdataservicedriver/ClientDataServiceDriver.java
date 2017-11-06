package server_bldriver.clientdataservicedriver;

import data.datautility.ResultMessage;
import dataservice.clientdataservice.ClientDataService;
import org.junit.Test;
import server_dataservicestub.clientdataservicestub.ClientDataServiceStub;

import static org.junit.Assert.*;

public class ClientDataServiceDriver {
    ClientDataService service=new ClientDataServiceStub();

    @Test
    public void find() throws Exception {
        assertEquals(1,service.find(null).size());
    }

    @Test
    public void insert() throws Exception {
        assertEquals(ResultMessage.success,service.insert(null));
    }

    @Test
    public void delete() throws Exception {
        assertEquals(ResultMessage.success,service.delete(null));
    }

    @Test
    public void update() throws Exception {
        assertEquals(ResultMessage.success,service.update(null));
    }

    @Test
    public void getID() throws Exception {
        assertEquals("123",service.getID());
    }

}