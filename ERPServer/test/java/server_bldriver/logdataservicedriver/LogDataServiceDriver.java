package stub_driver.Server.test.java;

import businesslogic.blutility.ResultMessage;
import dataservice.logdataservice.LogDataService;
import org.junit.Test;
import po.LogPO;
import stub_driver.Server.main.java.LogDataServiceStub;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by wangn on 2017.10.22.
 */
public class LogDataServiceDriver {

    LogDataService logDataService = new LogDataServiceStub();
    @Test
    public void find() throws Exception {
        assertEquals(logDataService.find(2017).getOperator(),"finance");
    }

    @Test
    public void insert() throws Exception {
        assertEquals(logDataService.insert(new LogPO("finance","add new CashBill",new Date())), ResultMessage.success);
    }

    @Test
    public void delete() throws Exception {
        assertEquals(logDataService.delete(new LogPO("finance","add new CashBill",new Date())),ResultMessage.success);
    }

    @Test
    public void update() throws Exception {
        assertEquals(logDataService.update(new LogPO("finance","add new CashBill",new Date())),ResultMessage.success);
    }

    @Test
    public void init() throws Exception {
        assertEquals(logDataService.init(),ResultMessage.success);
    }

    @Test
    public void finish() throws Exception {
        assertEquals(logDataService.finish(),ResultMessage.success);
    }

}