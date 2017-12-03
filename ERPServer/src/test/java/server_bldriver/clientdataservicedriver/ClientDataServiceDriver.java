package test.java.server_bldriver.clientdataservicedriver;

import main.java.dataservice.clientdataservice.ClientDataService;
import main.java.po.client.ClientPO;
import main.java.server_dataservicestub.clientdataservicestub.ClientDataServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientDataServiceDriver {
    ClientDataService service = new ClientDataServiceStub();

    @Test
    public void find() throws Exception {
        assertEquals(1, service.find(null).size());
    }

    @Test
    public void insert() throws Exception {
        assertEquals("00000001",service.insert(new ClientPO()));
    }

}