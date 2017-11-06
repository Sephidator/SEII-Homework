package server_bldriver.initialdataservicedriver;

import data.datautility.ResultMessage;
import dataservice.initialdataservice.InitialDataService;
import org.junit.Test;
import po.initial.InitialPO;
import po.initial.InitialQueryPO;
import server_dataservicestub.initialdataservicestub.InitialDataServiceStub;

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