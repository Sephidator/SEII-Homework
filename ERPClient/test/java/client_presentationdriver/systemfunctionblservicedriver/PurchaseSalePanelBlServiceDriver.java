package client_presentationdriver.systemfunctionblservicedriver;

import businesslogicservice.systemfunctionicservice.PurchaseSalePanelBlService;
import client_blservicestub.systemfunctionblservicestub.PurchaseSalePanelBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PurchaseSalePanelBlServiceDriver {
    PurchaseSalePanelBlService service=new PurchaseSalePanelBlServiceStub();

    @Test
    public void getMessageList() throws Exception {
        assertEquals("message",service.getMessage(null).getMessageList().get(0));
    }

}