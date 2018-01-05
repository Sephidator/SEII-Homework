package test.java.client_presentationdriver.logblservicedriver;

import main.java.businesslogicservice.logblservice.LogBlService;
import main.java.client_blservicestub.logblservicestub.LogBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogBlServiceDriver {
    LogBlService service=new LogBlServiceStub();

    @Test
    public void getLogList() throws Exception {
        assertEquals(0,service.getLogList(null).size());
    }

}