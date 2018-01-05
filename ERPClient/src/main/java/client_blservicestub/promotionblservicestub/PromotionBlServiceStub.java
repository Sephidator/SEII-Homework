package main.java.client_blservicestub.promotionblservicestub;

import main.java.businesslogicservice.promotionblservice.PromotionBlService;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;
import main.java.vo.promotion.PromotionQueryVO;
import main.java.vo.promotion.PromotionVO;

import java.util.ArrayList;

public class PromotionBlServiceStub implements PromotionBlService {

    @Override
    public ArrayList<PromotionVO> getPromotionList(PromotionQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public String addPromotion(PromotionVO vo) throws Exception {
        return "";
    }

    @Override
    public void editPromotion(PromotionVO vo) throws Exception {

    }

    @Override
    public void deletePromotion(String promotionID) throws Exception {

    }

    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception {
        return new ArrayList<>();
    }
}
