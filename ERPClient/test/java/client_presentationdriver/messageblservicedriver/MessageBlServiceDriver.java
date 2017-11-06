package client_presentationdriver.messageblservicedriver;

import businesslogicservice.messageblservice.MessageBlService;
import client_blservicestub.messageblservicestub.MessageBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageBlServiceDriver {
    MessageBlService service=new MessageBlServiceStub();

    @Test
    public void getMessageList() throws Exception {
        assertEquals(1,service.getMessageList(null).size());
    }

}