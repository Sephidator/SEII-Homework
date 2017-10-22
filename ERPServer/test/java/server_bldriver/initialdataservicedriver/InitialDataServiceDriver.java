package server_bldriver.initialdataservicedriver;

import data.datautility.ResultMessage;
import dataservice.initialdataservice.InitialDataService;
import org.junit.Test;
import po.InitialPO;
import server_dataservicestub.initialdataservicestub.InitialDataServiceStub;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by wangn on 2017.10.22.
 */
public class InitialDataServiceDriver {

    InitialDataService initialDataService = new InitialDataServiceStub();
    @Test
    public void find() throws Exception {
        assertEquals(initialDataService.find(2017).getInitYear(),2017);
    }

    @Test
    public void insert() throws Exception {
        assertEquals(initialDataService.insert(new InitialPO(2017, new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>())),2017);
    }

    @Test
    public void delete() throws Exception {
        assertEquals(initialDataService.delete(new InitialPO(2017, new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>())),ResultMessage.success);
    }

    @Test
    public void update() throws Exception {
        assertEquals(initialDataService.update(new InitialPO(2017, new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>())),ResultMessage.success);
    }

    @Test
    public void init() throws Exception {
        assertEquals(initialDataService.init(),ResultMessage.success);
    }

    @Test
    public void finish() throws Exception {
        assertEquals(initialDataService.finish(), ResultMessage.success);
    }

}