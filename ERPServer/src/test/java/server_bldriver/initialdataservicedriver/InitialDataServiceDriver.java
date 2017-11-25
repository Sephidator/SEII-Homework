package test.java.server_bldriver.initialdataservicedriver;

import main.java.data.datautility.ResultMessage;
import main.java.dataservice.initialdataservice.InitialDataService;
import org.junit.Test;
import main.java.po.initial.InitialPO;
import main.java.po.initial.InitialQueryPO;
import main.java.server_dataservicestub.initialdataservicestub.InitialDataServiceStub;

import static org.junit.Assert.*;

public class InitialDataServiceDriver {
    InitialDataService initialDataService = new InitialDataServiceStub();
    @Test
    public void insert() throws Exception {
        assertEquals(ResultMessage.success,initialDataService.insert(new InitialPO()));
    }

    @Test
    public void find() throws Exception {
        assertEquals(2017,initialDataService.find(new InitialQueryPO()).get(0).getYear());
    }

}