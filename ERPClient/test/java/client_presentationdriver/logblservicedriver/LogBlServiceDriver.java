package stub_driver.Client.test.java;

import businesslogicservice.logblservice.LogBlService;
import org.junit.Test;
import stub_driver.Client.main.java.LogBlServiceStub;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by wangn on 2017.10.22.
 */
public class LogBlServiceDriver {

    LogBlService logBlService = new LogBlServiceStub();
    @Test
    public void getLog() throws Exception {
        assertEquals(logBlService.getLog(new Date(),new Date()).get(0).getOperatorList().get(0),"finance");
    }

    @Test
    public void writeLog() throws Exception {
        assertEquals(logBlService.writeLog("","",new Date()), LogBlService.ResultMessage.Success);
    }

}