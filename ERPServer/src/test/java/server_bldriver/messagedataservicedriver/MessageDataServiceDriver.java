package test.java.server_bldriver.messagedataservicedriver;

import main.java.dataservice.messagedataservice.MessageDataService;
import main.java.server_dataservicestub.messagedataservicestub.MessageDataServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageDataServiceDriver {
    @Test
    public void find() throws Exception {
        MessageDataService service=new MessageDataServiceStub();
        assertEquals(1,service.find(null).size());
    }

}