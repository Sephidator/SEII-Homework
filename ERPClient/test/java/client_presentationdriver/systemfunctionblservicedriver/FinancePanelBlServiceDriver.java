package client_presentationdriver.systemfunctionblservicedriver;

import businesslogicservice.systemfunctionicservice.FinancePanelBlService;
import client_blservicestub.systemfunctionblservicestub.FinancePanelBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FinancePanelBlServiceDriver {
    FinancePanelBlService service=new FinancePanelBlServiceStub();

    @Test
    public void getMessageList() throws Exception {
        assertEquals("message",service.getMessage(null).getMessageList().get(0));
    }

}