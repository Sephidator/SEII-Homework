package test.java.server_bldriver.logdataservicedriver;

import main.java.dataservice.logdataservice.LogDataService;
import main.java.server_dataservicestub.logdataservicestub.LogDataServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogDataServiceDriver {
    @Test
    public void find() throws Exception {
        LogDataService service=new LogDataServiceStub();
        assertEquals(1,service.find(null).size());
    }

}