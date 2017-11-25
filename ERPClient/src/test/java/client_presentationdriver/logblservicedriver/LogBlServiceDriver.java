package test.java.client_presentationdriver.logblservicedriver;

import main.java.businesslogicservice.logblservice.LogBlService;
import main.java.client_blservicestub.logblservicestub.LogBlServiceStub;
import org.junit.Test;
import main.java.vo.log.LogQueryVO;

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