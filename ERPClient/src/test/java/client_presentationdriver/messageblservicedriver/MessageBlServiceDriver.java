package test.java.client_presentationdriver.messageblservicedriver;

import main.java.businesslogicservice.messageblservice.MessageBlService;
import main.java.client_blservicestub.messageblservicestub.MessageBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageBlServiceDriver {
    MessageBlService service=new MessageBlServiceStub();

    @Test
    public void getMessageList() throws Exception {
        assertEquals(0,service.getMessageList(null).size());
    }

    @Test
    public void deleteMessage() throws Exception {
    }

}