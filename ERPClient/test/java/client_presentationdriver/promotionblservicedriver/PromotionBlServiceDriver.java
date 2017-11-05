package client_presentationdriver.promotionblservicedriver;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.promotionblservice.PromotionBlService;
import client_blservicestub.promotionblservicestub.PromotionBlServiceStub;
import org.junit.Test;
import vo.promotion.PromotionGoodsVO;
import vo.promotion.PromotionTotalVO;
import vo.promotion.PromotionVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.*;

public class PromotionBlServiceDriver {
    PromotionBlService promotionBlService = new PromotionBlServiceStub();
    ArrayList<PromotionVO> vos = new ArrayList<>();

    @Test
    public void show() throws Exception {
        vos.clear();
        PromotionTotalVO vo1 = new PromotionTotalVO("001", 2, new Date(), new Date(), 1000, 100, new HashMap<>());
        PromotionGoodsVO vo2 = new PromotionGoodsVO("002", 1, new Date(), new Date(), new HashMap<>(), 300);
        vos.add(vo1);
        vos.add(vo2);
        assertEquals(promotionBlService.show().toString(), vos.toString());
    }

    @Test
    public void add() throws Exception {
        assertEquals(promotionBlService.add(null), ResultMessage.success);
    }

    @Test
    public void update() throws Exception {
        assertEquals(promotionBlService.update(null), ResultMessage.success);
    }

    @Test
    public void delete() throws Exception {
        assertEquals(promotionBlService.delete(null), ResultMessage.success);
    }

}