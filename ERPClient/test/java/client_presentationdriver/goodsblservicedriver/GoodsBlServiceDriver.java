package client_presentationdriver.goodsblservicedriver;

import businesslogicservice.goodsblservice.GoodsBlService;
import client_blservicestub.goodsblservicestub.GoodsBlServiceStub;
import org.junit.Test;
import businesslogic.blutility.ResultMessage;
import static org.junit.Assert.*;

public class GoodsBlServiceDriver {
    GoodsBlService goodsBlService=new GoodsBlServiceStub();

    @Test
    public void insertGoods() throws Exception {

        assertEquals(ResultMessage.success,goodsBlService.insertGoods("000000002","灯泡B","000000001","B型号",
                150,11.0,19.0,14.0,21.0,"无"));
    }

    @Test
    public void getID() throws Exception {
        assertEquals("000000002",goodsBlService.getID());
    }

    @Test
    public void deleteGoods() throws Exception {
        assertEquals(ResultMessage.success,goodsBlService.deleteGoods("000000001"));
    }

    @Test
    public void updateGoods() throws Exception {
        assertEquals(ResultMessage.success,goodsBlService.updateGoods("000000002","灯泡B","000000001","B型号",
                100,10.0,20.0,12.0,19.0,"无"));
    }

    @Test
    public void detailGoods() throws Exception {

        assertEquals(ResultMessage.success,goodsBlService.detailGoods("000000002"));
    }

    @Test
    public void setAlarmNum() throws Exception {
        assertEquals(ResultMessage.success,goodsBlService.setAlarmNum("000000002",20));


    }

}