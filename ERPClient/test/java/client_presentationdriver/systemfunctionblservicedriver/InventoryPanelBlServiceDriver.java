package client_presentationdriver.systemfunctionblservicedriver;

import businesslogicservice.systemfunctionicservice.InventoryPanelBlService;
import client_blservicestub.systemfunctionblservicestub.InventoryPanelBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InventoryPanelBlServiceDriver {
    InventoryPanelBlService service=new InventoryPanelBlServiceStub();

    @Test
    public void getMessageList() throws Exception {
        assertEquals("message",service.getMessage(null).getMessageList().get(0));
    }

}