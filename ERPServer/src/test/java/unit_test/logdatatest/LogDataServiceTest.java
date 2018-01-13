package test.java.unit_test.logdatatest;

import main.java.dataservice.logdataservice.LogDataService;
import org.junit.Test;

import java.rmi.Naming;

import static org.junit.Assert.assertEquals;

public class LogDataServiceTest {
    LogDataService service;

    public LogDataServiceTest() throws Exception {
        service = (LogDataService) Naming.lookup("rmi://127.0.0.1:7200/LogDataService");
    }

    @Test
    public void finds() throws Exception {
        assertEquals(true, service.finds(null).size() >= 0);
    }

}