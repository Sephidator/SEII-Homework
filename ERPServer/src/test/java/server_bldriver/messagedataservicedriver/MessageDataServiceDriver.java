package server_bldriver.messagedataservicedriver;

import data.datautility.ResultMessage;
import dataservice.messagedataservice.MessageDataService;
import org.junit.Test;
import server_dataservicestub.messagedataservicestub.MessageDataServiceStub;

import static org.junit.Assert.*;

public class MessageDataServiceDriver {
    MessageDataService service=new MessageDataServiceStub();

    @Test
    public void find() throws Exception {
        assertEquals(1,service.find(null).size());
    }

    @Test
    public void insert() throws Exception {
        assertEquals(ResultMessage.success,service.insert(null));
    }

}