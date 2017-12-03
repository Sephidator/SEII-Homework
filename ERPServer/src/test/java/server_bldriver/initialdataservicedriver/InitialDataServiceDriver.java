package test.java.server_bldriver.initialdataservicedriver;

import main.java.dataservice.initialdataservice.InitialDataService;
import main.java.po.initial.InitialPO;
import main.java.server_dataservicestub.initialdataservicestub.InitialDataServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class InitialDataServiceDriver {
    InitialDataService service=new InitialDataServiceStub();

    @Test
    public void find() throws Exception {
        assertEquals(1,service.find(null).size());
    }

    @Test
    public void insert() throws Exception {
        assertEquals("00000001",service.insert(new InitialPO()));
    }

}