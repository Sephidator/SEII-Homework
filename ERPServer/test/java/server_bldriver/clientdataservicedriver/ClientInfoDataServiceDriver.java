package server_bldriver.clientdataservicedriver;

import dataservice.clientdataservice.ClientInfoDataService;
import org.junit.Test;
import server_dataservicestub.clientdataservicestub.ClientInfoDataServiceStub;

import static org.junit.Assert.*;

public class ClientInfoDataServiceDriver {
    ClientInfoDataService service=new ClientInfoDataServiceStub();

    @Test
    public void getClient() throws Exception {
        assertEquals(service.getClient("123").getID(),"123");
    }

}