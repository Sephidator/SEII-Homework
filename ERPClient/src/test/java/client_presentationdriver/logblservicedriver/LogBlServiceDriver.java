package client_presentationdriver.logblservicedriver;

import businesslogicservice.logblservice.LogBlService;
import client_blservicestub.logblservicestub.LogBlServiceStub;
import org.junit.Test;
import vo.log.LogQueryVO;

import static org.junit.Assert.*;

/**
 * Created by wangn on 2017.11.06.
 */
public class LogBlServiceDriver {
    LogBlService logBlService= new LogBlServiceStub();
    @Test
    public void getLogList() throws Exception {
        assertEquals(20,logBlService.getLogList(new LogQueryVO()).get(0).getOperator().getAge());
    }

}