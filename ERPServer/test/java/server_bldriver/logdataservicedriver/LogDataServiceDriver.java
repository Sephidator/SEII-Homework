package server_bldriver.logdataservicedriver;

import data.datautility.ResultMessage;
import dataservice.logdataservice.LogDataService;
import org.junit.Test;
import po.LogPO;
import server_dataservicestub.logdataservicestub.LogDataServiceStub;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by wangn on 2017.10.22.
 */
public class LogDataServiceDriver {

    LogDataService logDataService = new LogDataServiceStub();
    @Test
    public void find() throws Exception {
        String list= "finance";
        assertEquals(list,logDataService.find(2017).getOperator());
    }

    @Test
    public void insert() throws Exception {
        assertEquals(logDataService.insert(new LogPO(null,null,null)), ResultMessage.success);
    }

    @Test
    public void delete() throws Exception {
        assertEquals(logDataService.delete(new LogPO(null,null,null)),ResultMessage.success);
    }

    @Test
    public void update() throws Exception {
        assertEquals(logDataService.update(new LogPO(null,null,null)),ResultMessage.success);
    }

    @Test
    public void init() throws Exception {
        assertEquals(logDataService.init(), ResultMessage.success);
    }

    @Test
    public void finish() throws Exception {
        assertEquals(logDataService.finish(),ResultMessage.success);
    }

}