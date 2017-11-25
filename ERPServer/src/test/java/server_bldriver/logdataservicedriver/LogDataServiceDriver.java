package test.java.server_bldriver.logdataservicedriver;

import main.java.data.datautility.ResultMessage;
import main.java.dataservice.logdataservice.LogDataService;
import org.junit.Test;
import main.java.po.log.LogPO;
import main.java.po.log.LogQueryPO;
import main.java.server_dataservicestub.logdataservicestub.LogDataServiceStub;

import static org.junit.Assert.*;

public class LogDataServiceDriver {
    LogDataService logDataService = new LogDataServiceStub();
    @Test
    public void find() throws Exception {
        assertEquals("Finance-20171106-00001",logDataService.find(new LogQueryPO()).get(0).getOperatorID());
    }

    @Test
    public void insert() throws Exception {
        assertEquals(ResultMessage.success,logDataService.insert(new LogPO()));
    }

    @Test
    public void update() throws Exception {
        assertEquals(ResultMessage.success,logDataService.update(new LogPO()));
    }

}