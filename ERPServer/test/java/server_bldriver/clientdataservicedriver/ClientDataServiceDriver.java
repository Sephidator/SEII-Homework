package server_bldriver.clientdataservicedriver;

import data.datautility.ResultMessage;
import dataservice.clientdataservice.ClientDataService;
import po.ClientPO;
import server_dataservicestub.clientdataservicestub.ClientDataServiceStub;

import static org.junit.Assert.*;

public class ClientDataServiceDriver {
    ClientDataService service=new ClientDataServiceStub();

    @org.junit.Test
    public void insert() throws Exception {
        assertEquals(ResultMessage.success,service.insert(new ClientPO()));
    }

    @org.junit.Test
    public void find() throws Exception {
        assertEquals(service.find("教师").size(), 2);
    }

    @org.junit.Test
    public void delete() throws Exception {
        assertEquals(ResultMessage.success,service.delete(new ClientPO()));
    }

    @org.junit.Test
    public void update() throws Exception {
        assertEquals(ResultMessage.success, service.update(new ClientPO()));
    }

}