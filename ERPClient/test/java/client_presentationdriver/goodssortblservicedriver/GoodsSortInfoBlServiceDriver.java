package client_presentationdriver.goodssortblservicedriver;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.goodssortblservice.GoodsSortInfoBlService;
import client_blservicestub.goodssortblservicestub.GoodsSortInfoBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoodsSortInfoBlServiceDriver {

    GoodsSortInfoBlService goodsSortInfoBlService=new GoodsSortInfoBlServiceStub();

    @Test
    public void show() throws Exception {

        assertEquals(ResultMessage.success,goodsSortInfoBlService.show("000000001"));
    }

}