package client_presentationdriver.systemfunctionblservicedriver;

import businesslogicservice.systemfunctionicservice.ManagerPanelBlService;
import client_blservicestub.systemfunctionblservicestub.ManagerPanelBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ManagerPanelBlServiceDriver {
    ManagerPanelBlService service=new ManagerPanelBlServiceStub();

    @Test
    public void getMessageList() throws Exception {
        assertEquals("message",service.getMessage(null).getMessage());
    }

}