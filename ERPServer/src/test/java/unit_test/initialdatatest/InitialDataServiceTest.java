package test.java.unit_test.initialdatatest;

import main.java.dataservice.initialdataservice.InitialDataService;
import main.java.exception.ExistException;
import main.java.po.initial.InitialPO;
import org.junit.Test;

import java.rmi.Naming;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class InitialDataServiceTest {
    InitialDataService service;

    public InitialDataServiceTest() throws Exception {
        service = (InitialDataService) Naming.lookup("rmi://127.0.0.1:7200/InitialDataService");
    }

    @Test
    public void finds() throws Exception {
        assertEquals(true, service.finds(null).size() >= 0);
    }

    @Test(expected = ExistException.class)
    public void insert() throws Exception {

        assertEquals("Initial", service.insert(new InitialPO(2017, new ArrayList<>(), new ArrayList<>(), new ArrayList<>())).substring(0, 7));
    }

}