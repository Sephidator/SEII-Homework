package client_presentationdriver.goodsblservicedriver;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.goodsblservice.GoodsBLService;
import client_blservicestub.goodsblservicestub.GoodsBLServiceStub;
import org.junit.Test;
import vo.goods.GoodsQueryVO;
import vo.goods.GoodsVO;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GoodsBLServiceDriver {

    GoodsBLService goodsBLService=new GoodsBLServiceStub();
    GoodsVO goodsVO=new GoodsVO();
    GoodsQueryVO goodsQueryVO=new GoodsQueryVO();


    @Test
    public void getGoodsList() throws Exception {
        ArrayList<GoodsVO> goodsVOS=new ArrayList<GoodsVO>();
        goodsVOS.add(null);
        assertEquals(goodsVOS,goodsBLService.getGoodsList(goodsQueryVO));
    }

    @Test
    public void addGoods() throws Exception {
        assertEquals(ResultMessage.success,goodsBLService.addGoods(goodsVO));
    }

    @Test
    public void editGoods() throws Exception {
        assertEquals(ResultMessage.success,goodsBLService.addGoods(goodsVO));
    }

    @Test
    public void deleteGoods() throws Exception {
        assertEquals(ResultMessage.success,goodsBLService.addGoods(goodsVO));
    }

    @Test
    public void getGoodsID() throws Exception {
        assertEquals("C0002",goodsBLService.getGoodsID());
    }

}