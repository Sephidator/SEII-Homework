package client_presentationdriver.logblservicedriver;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.logblservice.LogBlService;
import client_blservicestub.logblservicestub.LogBlServiceStub;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by wangn on 2017.10.22.
 */
public class LogBlServiceDriver {

    LogBlService logBlService = new LogBlServiceStub();
    @Test
    public void getLog() throws Exception {
        assertEquals(logBlService.getLog(new Date(),new Date()).get(0).getOperator().getName(),"LiuQin");
    }

    @Test
    public void writeLog() throws Exception {
        assertEquals(logBlService.writeLog("","",new Date()), ResultMessage.success);
    }

}