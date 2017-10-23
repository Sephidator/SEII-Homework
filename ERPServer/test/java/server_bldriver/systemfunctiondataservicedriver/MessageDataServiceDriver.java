package server_bldriver.systemfunctiondataservicedriver;

import dataservice.systemfunctiondataservice.MessageDataService;
import org.junit.Test;
import server_dataservicestub.systemfunctiondataservicestub.MessageDataServiceStub;

import static org.junit.Assert.assertEquals;

public class MessageDataServiceDriver {
    MessageDataService service=new MessageDataServiceStub();

    @Test
    public void getMessage() throws Exception {
        assertEquals(service.getMessage(null).getMessageList().get(0), "message");
    }

}