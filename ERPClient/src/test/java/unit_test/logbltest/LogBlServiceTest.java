package test.java.unit_test.logbltest;

import main.java.businesslogicfactory.logblfactory.LogBlFactory;
import main.java.businesslogicservice.logblservice.LogBlService;
import main.java.client_blservicestub.logblservicestub.LogBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LogBlServiceTest {
    LogBlService service= LogBlFactory.getService();

    @Test
    public void getLogList() throws Exception {
        assertEquals(true,service.getLogList(null).size()>=0);
    }

}