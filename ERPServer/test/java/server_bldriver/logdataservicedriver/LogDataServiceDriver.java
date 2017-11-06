package server_bldriver.logdataservicedriver;

import data.datautility.ResultMessage;
import dataservice.logdataservice.LogDataService;
import org.junit.Test;
import po.log.LogPO;
import po.log.LogQueryPO;
import server_dataservicestub.logdataservicestub.LogDataServiceStub;

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