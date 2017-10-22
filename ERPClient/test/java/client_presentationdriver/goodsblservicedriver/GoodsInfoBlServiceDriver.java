package client_presentationdriver.goodsblservicedriver;

import businesslogicservice.goodsblservice.GoodsInfoBlService;
import client_blservicestub.goodsblservicestub.GoodsInfoBlServiceStub;
import org.junit.Test;
import businesslogic.blutility.ResultMessage;
import static org.junit.Assert.*;

public class GoodsInfoBlServiceDriver {

    GoodsInfoBlService goodsInfoBlService=new GoodsInfoBlServiceStub();

    @Test
    public void show() throws Exception {

        assertEquals(ResultMessage.success,goodsInfoBlService.show("000000002"));
    }

}