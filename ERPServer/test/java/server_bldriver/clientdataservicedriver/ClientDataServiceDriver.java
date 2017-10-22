package server_bldriver.clientdataservicedriver;

import data.datautility.ResultMessage;
import dataservice.clientdataservice.ClientDataService;
import po.ClientPO;
import server_dataservicestub.clientdataservicestub.ClientDataServiceStub;

import static org.junit.Assert.*;

public class ClientDataServiceDriver {
    ClientDataService service=new ClientDataServiceStub();

    @org.junit.Test
    public void add() throws Exception {
        assertEquals(service.add(new ClientPO()), ResultMessage.success);
    }

    @org.junit.Test
    public void find() throws Exception {
        assertEquals(service.find("教师").size(), 2);
    }

    @org.junit.Test
    public void delete() throws Exception {
        assertEquals(service.delete(new ClientPO()), ResultMessage.success);
    }

    @org.junit.Test
    public void update() throws Exception {
        assertEquals(service.update(new ClientPO()), ResultMessage.success);
    }

}