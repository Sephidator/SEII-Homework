package client_presentationdriver.systemfunctionblservicedriver;

import businesslogicservice.systemfunctionicservice.AdministratorPanelBlService;
import client_blservicestub.systemfunctionblservicestub.AdministratorPanelBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdministratorPanelBlServiceDriver {
    AdministratorPanelBlService service=new AdministratorPanelBlServiceStub();

    @Test
    public void getMessageList() throws Exception {
        assertEquals("message",service.getMessage(null).getMessage());
    }

}