package test.java.unit_test.messagedatatest;

import main.java.dataservice.messagedataservice.MessageDataService;
import org.junit.Test;

import java.rmi.Naming;

import static org.junit.Assert.assertEquals;

public class MessageDataServiceTest {
    MessageDataService service;

    public MessageDataServiceTest() throws Exception {
        service = (MessageDataService) Naming.lookup("rmi://127.0.0.1:7200/MessageDataService");
    }

    @Test
    public void finds() throws Exception {
        assertEquals(true, service.finds("User00000001").size() >= 0);
    }

}