package test.java.unit_test.initialdatatest;

import main.java.dataservice.initialdataservice.InitialDataService;
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

    @Test
    public void insert() throws Exception {
        ArrayList<InitialPO> list = service.finds(null);
        InitialPO initialPO = list.get(list.size() - 1);

        assertEquals("Initial", service.insert(new InitialPO(initialPO.getYear() + 1, new ArrayList<>(), new ArrayList<>(), new ArrayList<>())).substring(0, 7));
    }

}